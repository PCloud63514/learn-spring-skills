# Gradle을 사용하여 편하게 배포하기

서버 배포 시 매번 수정된 properties 파일과 jar 파일을 여러 번의 명령어를 통해 이동시키는 것이 번거로워 해결했던 방법을 작성하였습니다.

> 현재 dockerfile을 통한 배포 및 업데이트를 진행하고 있으므로 추후 내용을 포함하도록 하겠습니다.

 

우선 제가 진행했던 프로젝트에서 배포할 내용은 다음과 같습니다.

- application.yml & properties 파일
- jar 파일
- 외부 서버 호출 전용 lib 폴더
- 암호화 및 인증에 필요한 cert 폴더

목표는 **build -> 생성된 파일 run 디렉토리로 이동입니다.**

 

### 구성

**build.gradle에 내용 추가**

doLast는 task의 동작 끝에 추가 동작을 설정하는 함수입니다.

```groovy
ext {
    rootProjectName = getRootProject().name
    outputPath = "run"
    jarE = ".jar"
}

tasks.build.doLast {
    println "buildRun Start"
    mkdir outputPath

    copy {
        from "lib"
        include "**"
        into "$outputPath/lib"
    }

    copy {
        from "cert"
        include "**"
        into "$outputPath/cert"
    }

    copy {
        from "build/libs"
        include "$rootProjectName-$version$jarE"
        into outputPath
        rename "$rootProjectName-$version$jarE", "app.jar"
    }

    copy {
        from "src/main/resources"
        include "*.yml"
        into outputPath

    }

    copy {
        from "src/main/resources"
        include "*.properties"
        into outputPath
    }
}
```



이후 gradle build 명령어를 사용 시 run 폴더에 배포에 필요한 파일이 구성되는 것을 확인할 수 있습니다.



![image](https://user-images.githubusercontent.com/22608825/145991912-27b168e2-9bf2-4305-81a9-bf72964ad937.png)

만약 이미 구성되어 있는 서버에서 build 시 jar 파일이 생성되지 않는다면 두 가지 방법이 있습니다.

 

#### 1. id 'war' 제거

gradle build 명령어를 사용해도 jar가 생성되지 않는다면 war 파일이 생성되는 등의 결과가 나올 것이므로 배포에 필요 없을 시 제거합니다.

```groovy
plugins {
    id 'org.springframework.boot' version '2.5.7'
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id 'java'
}
```



#### 2. 다른 형식의 gradle 파일 작성

**dependsOn는** task 동작 이전에 우선 동작되어야 함을 지정하는 함수입니다.

이를 이용하여 buildRun이 동작하기 위해선 bootJar가 필요하고 bootJar가 동작하기 위해 build가 필요하도록 변경합니다.

이후 gradle buildRun을 입력하면 정상적인 동작이 가능합니다.

```groovy
ext {
    rootProjectName = getRootProject().name
    outputPath = "run"
    jarE = ".jar"
}

task buildRun {
    doLast {
        println "buildRun Start"
        mkdir outputPath

        copy {
            from "lib"
            include "**"
            into "$outputPath/lib"
        }

        copy {
            from "cert"
            include "**"
            into "$outputPath/cert"
        }

        copy {
            from "build/libs"
            include "$rootProjectName-$version$jarE"
            into outputPath
            rename "$rootProjectName-$version$jarE", "app.jar"
        }

        copy {
            from "src/main/resources"
            include "*.yml"
            into outputPath

        }

        copy {
            from "src/main/resources"
            include "*.properties"
            into outputPath
        }
    }
}

tasks.bootJar.dependsOn(build)
tasks.buildRun.dependsOn(bootJar)
```

TISTORY BLOG https://pcloud.tistory.com/33
