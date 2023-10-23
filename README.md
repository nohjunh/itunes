![image](https://github.com/nohjunh/assignment/assets/75293768/5abacb52-1187-4b69-85e5-971f9739b7ba)

<p align="center">
  <a><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.20-orange?style=flat"/></a>
  <a><img alt="Java version" src="https://img.shields.io/badge/Java version-17-orange?style=flat"/></a>
  <a><img alt="minSdk" src="https://img.shields.io/badge/minSdkVersion-23-orange?style=flat"/></a>
  <a><img alt="targetSdk" src="https://img.shields.io/badge/targetSdkVersion-34-orange?style=flat"/></a>
</p>

---

## 🚦 Required Detailed Specifications
* iTunes search API를 호출하고, 결과 트랙 리스트를 나열한다.
* Pagination을 구현하여 트랙들을 리스트에 보여준다.
* limit, offset parameter를 이용한다.

---

## 📹 Preview
* iTunes search API를 Pagingnation을 이용해 호출하고, 응답으로 받은 TrackList를 표시한다.</br>
* iTunes Search API를 통해 음악 트랙을 검색할 때 에러 상황에 대한 대응 처리를 한다. </br>
* Pagination을 통해 다음 트랙리스트를 불러와야 하기에 관련 처리도 함께 진행한다. </br>
* 원하는 TrackItem을 보관함에 저장하는 기능 추가 구현

| 영상 | 설명 |
| --- | ---- |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/b339392f-6736-46d6-9d30-215f4173892b.gif" alt="drawing" width="250px" /> | iTunes search API를 Pagingnation을 이용해 호출하고, </br> 응답으로 받은 TrackList를 표시 |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/3014bcd9-b59e-4cdc-85e3-5006fde996c6.gif" alt="drawing" width="250px" /> | 초기 검색 데이터를 불러오기도 전에 에러가 발생할 경우, SearchScreen 전체를 Error 발생을 인지할 수 있게 표시하고, 다시 시도할 수 있는 인터페이스를 표시한다. |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/e3656507-7b63-4a7e-8910-3e83b2cc9c7a.gif" alt="drawing" width="250px" /> | 트랙리스트를 불러온 이후, Pagination을 통해 새로운 트랙을 불러오는 과정에서 Error 발생 시 다시 Load할 수 있는 인터페이스를 표시한다. |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/3d8b5aaa-aaf8-4aea-8dd4-b415b026a495.gif" alt="drawing" width="250px" /> | **트랙 보관 :** 검색 결과 중 원하는 트랙을 TrackItem을 보관함에 저장하려면, TrackItem의 우측에 있는 보관함 아이콘을 클릭 </br></br> **StorageScreen으로 전환 시 저장된 트랙 불러오기:** StorageScreen으로 전환 시 storage_track_resource 테이블에서 저장된 TrackList를 불러와 리스트 표시 </br></br> **트랙 삭제 :** StorageScreen에서 각 TrackItem의 우측 상단에 있는 Cancel Icon을 클릭하면 해당 TrackItem을 삭제 |
| <img src="https://github.com/nohjunh/assignment/assets/75293768/d533a28d-3fd4-483e-8c73-55396d6df744.gif" alt="drawing" width="250px" /> | TrackList Loading 과정에서 ShimmerEffect를 적용하여 UX 개선 |

---

## 🔑 Build Project
프로젝트의 "local.properties" 파일에 다음과 같이 API_KEY를 추가한다.

<img width="271" alt="image" src="https://github.com/nohjunh/assignment/assets/75293768/a898c26e-486c-432f-94c9-5dc39a9379a9">


---

## 🏢 Architecture Overview

![image](https://github.com/nohjunh/assignment/assets/75293768/e6e321fb-9b04-48e0-bd1a-a5aa81dc2557)

* 본 프로젝트는 데이터, 도메인, UI 레이어를 갖는 단방향 데이터 흐름의 반응형 프로그래밍 모델을 따른다. 
UI 레이어와 같은 상위 레이어가 하위 레이어의 데이터를 제공받기 위해 이벤트 호출하고 그 데이터는 하위 레이어에서 상위 레이어로 흐르는 구조로 진행된다.

<img width="461" alt="image" src="https://github.com/nohjunh/assignment/assets/75293768/80c3647e-670e-4a80-abb4-8ad8a873ac54">

* UI 레이어는 JetPack Compose를 사용하여 빌드하였고, ViewModel에서 UseCase, Repository를 통한 데이터 스트림을 수신하고 이를 UI State로 변환한다.
* 사용자가 앱과의 상호 작용하는 방식은 ViewModel에서 이벤트로 처리

---

## 🏘 Modularization
멀티모듈화 전략을 채택함으로써 모듈 단위로 기능을 분리하여 코드 간의 의존성을 낮추고, 재사용성을 높이며, 빌드 시간을 줄이는 이점을 얻는다.

<table>
    <tr>
        <td>
            <img src="https://github.com/nohjunh/assignment/assets/75293768/b8b42c85-ee4f-41de-9a38-fc26fd4e64dc" alt="Image 1" width="100%">
        </td>
        <td>
            <img src="https://github.com/nohjunh/assignment/assets/75293768/d131f129-5c38-4dc0-85e6-5d5fd6231c8f" alt="Image 2" width="100%">
        </td>
    </tr>
</table>


- **App Module**
   
    앱 모듈은 모든 Feature 모듈과 필요한 핵심 모듈에 의존
    
- **Feature Module**

    앱에서 단일 책임을 처리할 수 있도록 범위가 지정된 기능별 모듈
    
- **Core Module**

   앱의 다른 모듈 간에 공유해야 하는 코드와 특정 종속성을 포함하는 일반 라이브러리 모듈

