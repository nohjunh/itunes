![image](https://github.com/nohjunh/assignment/assets/75293768/f5556c4c-9234-4452-a664-245bfe7d36e9)

<p align="center">
  <a><img alt="Studio" src="https://img.shields.io/badge/Studio-Giraffe 2022.3.1-orange?style=flat"/></a>
  <a><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.20-orange?style=flat"/></a>
  <a><img alt="Java version" src="https://img.shields.io/badge/Java version-17-orange?style=flat"/></a>
  <a><img alt="minSdk" src="https://img.shields.io/badge/minSdkVersion-23-orange?style=flat"/></a>
  <a><img alt="targetSdk" src="https://img.shields.io/badge/targetSdkVersion-34-orange?style=flat"/></a>
</p>

---

## 🚦 Detailed Specifications
* iTunes search API를 호출하고, 결과 트랙 리스트를 나열한다.
* Pagination을 구현하여 트랙들을 리스트에 보여준다.
* limit, offset parameter를 이용한다.
* Open API이기에 발생 가능한 중복 아이템에 대한 대응 

---

## 🔑 Build Project
프로젝트의 "local.properties" 파일에 다음과 같이 API BASE_URL을 추가한다.

<img width="263" alt="image" src="https://github.com/nohjunh/assignment/assets/75293768/91181f29-e6bb-48b8-9a8f-8214ca932c97">

---

## 📹 Preview
* iTunes search API를 Pagingnation을 이용해 호출하고, 응답으로 받은 TrackList를 표시한다.</br>
* iTunes Search API를 통해 음악 트랙을 검색할 때 에러 상황에 대한 대응 처리를 한다. </br>
* Pagination을 통해 다음 트랙리스트를 불러와야 하기에 관련 처리도 함께 진행한다. </br>
* 원하는 TrackItem을 보관함에 저장하는 기능 추가 구현

| 영상 | 설명 |
| --- | ---- |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/a50e8c60-396a-4210-aa17-895086882fc8.gif" alt="drawing" width="250px" /> | iTunes search API를 Pagingnation을 이용해 호출하고, </br> 응답으로 받은 TrackList를 표시 |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/130c0f6b-04b6-4f32-a58d-4c577af1abc3.gif" alt="drawing" width="250px" /> | 초기 검색 데이터를 불러오기도 전에 에러가 발생할 경우, SearchScreen 전체를 Error 발생을 인지할 수 있게 표시하고, 다시 시도할 수 있는 인터페이스를 표시한다. |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/a2fa0305-ceb1-4ec9-a6a2-dc159933245a.gif" alt="drawing" width="250px" /> | 트랙리스트를 불러온 이후, Pagination을 통해 새로운 트랙을 불러오는 과정에서 Error 발생 시 다시 Load할 수 있는 인터페이스를 표시한다. |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/f89a8a08-99af-401a-8e7b-de3a399d4e4f.gif" alt="drawing" width="250px" /> | **트랙 보관 :** 검색 결과 중 원하는 트랙을 TrackItem을 보관함에 저장하려면, TrackItem의 우측에 있는 보관함 아이콘을 클릭 </br></br> **StorageScreen으로 전환 시 저장된 트랙 불러오기:** StorageScreen으로 전환 시 storage_track_resource 테이블에서 저장된 TrackList를 불러와 리스트 표시 </br></br> **트랙 삭제 :** StorageScreen에서 각 TrackItem의 우측 상단에 있는 Cancel Icon을 클릭하면 해당 TrackItem을 삭제 |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/2a961870-1d76-4cc4-b6b8-cdff7c4f1d5d.gif" alt="drawing" width="250px" /> | TrackList Loading 과정에서 ShimmerEffect를 적용하여 UX 개선 |

---

## 🏢 Architecture Overview

![image](https://github.com/nohjunh/assignment/assets/75293768/05cc3011-0109-45ac-bf33-3c553159b957)

* 본 프로젝트는 데이터, 도메인, UI 레이어를 갖는 단방향 데이터 흐름의 반응형 프로그래밍 모델을 따른다. 
UI 레이어와 같은 상위 레이어가 하위 레이어의 데이터를 제공받기 위해 이벤트 호출하고 그 데이터는 하위 레이어에서 상위 레이어로 흐르는 구조로 진행된다.

<img width="461" alt="스크린샷 2023-10-23 오후 9 30 12" src="https://github.com/nohjunh/assignment/assets/75293768/0d72491c-9ab9-4873-a654-7c56360b9fcd">

* UI 레이어는 JetPack Compose를 사용하여 빌드하였고, ViewModel에서 UseCase, Repository를 통한 데이터 스트림을 수신하고 이를 UI State로 변환한다.
* 사용자가 앱과의 상호 작용하는 방식은 ViewModel에서 이벤트로 처리

---

## 🏘 Modularization
멀티모듈화 전략을 채택함으로써 모듈 단위로 기능을 분리하여 코드 간의 의존성을 낮추고, 재사용성을 높이며, 빌드 시간을 줄이는 이점을 얻는다.

<table>
    <tr>
        <td>
            <img src="https://github.com/nohjunh/assignment/assets/75293768/7267cbc2-c4ef-4620-9578-46c3ffb44208.png" alt="Image 1" width="100%">
        </td>
        <td>
            <img src="https://github.com/nohjunh/assignment/assets/75293768/e7a16256-d78d-4a85-8dba-cc2ee2ef0bc5.png" alt="Image 2" width="100%">
        </td>
    </tr>
</table>


- **App Module**
   
    앱 모듈은 모든 Feature 모듈과 필요한 핵심 모듈에 의존
    
- **Feature Module**

    앱에서 단일 책임을 처리할 수 있도록 범위가 지정된 기능별 모듈
    
- **Core Module**

   앱의 다른 모듈 간에 공유해야 하는 코드와 특정 종속성을 포함하는 일반 라이브러리 모듈

