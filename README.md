# Project_WhereTrashcan
### 가로쓰레기통 위치 안내와 분리수거 가이드 어플리케이션
------------------------
* 프로젝트 소개
> 환경에 점차 많은 관심들이 쏟아지고 있는 지금 이 상황에도 길거리에 많은 무단 투기가 목격되고 있습니다. 특히 한강공원과 같이 사람들이 많이 몰리는 곳에서는 이러한 쓰레기 때문에 많은 사람들이 불편을 겪고 있습니다.
일회용품의 사용을 줄여 쓰레기의 규모를 줄이는 것도 중요하지만, 올바르게 쓰레기를 버리는 행위 또한 중요하다는 생각을 하게 되었습니다. 그렇지만 쓰레기통의 위치를 알지 못해 이러한 마음을 가지고도 실천하기에 어려움을 겪는다는 것을 느꼈고, 이를 해결하고자 "쓰레기통의 위치를 알려주면 어떨까?"하는 생각을 하게 되었습니다.
따라서 쓰레기통의 위치를 지도 위에 마커로 표시하여 알려주는 애플리케이션을 개발하게 되었습니다. 또한 쓰레기의 분리수거 방법을 알려주어 더욱 쉽게 분리배출을 돕는 기능을 추가하였습니다.

* 개발 배경 및 필요성
> 사람들이 많이 사용하고 있는 네이버 지도와 카카오 맵, 구글 등 그 어디에도 쓰레기통의 위치는 알려주지 않았습니다. 그래서 많은 사람들은 쓰레기를 버릴 때 종종 편의점을 이용하거나, 혹은 근처 화장실이나 식당 등을 이용합니다.
하지만, 이를 이용하기 어려운 상황일 때에는 종종 무단 투기를 현장이 많이 목격되고 있습니다. 이를 해결하고자 지도에 쓰레기통의 위치를 띄워주는 기능을 구현하고자 하였고, 앞으로 많은 사람들의 올바른 쓰레기 버리기 행위를 길거리가 깨끗해지기를 고대하고 있습니다.

* 프로젝트 특징과 장점
> 1. 현재 위치에서 가깝거나 사용자가 원하는 위치에 존재하는 쓰레기통의 위치를 마커로 통해 직관적이고 쉽게 확인할 수 있습니다.
> 2. 검색을 통해서 사용자가 원하는 위치를 검색해볼 수 있습니다.
> 3. 헷갈리는 분리수거의 종류를 검색을 통해서 확인이 가능합니다.
> 4. 가이드를 통해서 올바른 분리수거법 확인이 가능합니다.

* 요구사항 분석 및 정의
>  1. 쓰레기통 위치 확인
>     공공데이터포털에서 제공하는 가로 쓰레기통의 정보를 사용하여 해당 위도와 경도에 맞춰 쓰레기통 마커를 표시하였습니다. 해당 마커를 클릭하면 자세한 주소가 나타납니다.
>  2. 현 위치 확인
>     사용자가 현재 있는 위치를 GPS를 통해 받아옵니다. 가장 가까이에 있는 가로 쓰레기통 위치를 지도 상에 표시하여 편리한 서비스를 제공합니다.
>  3. 장소 검색
>     원하는 지역명 또는 도로명, 건물 이름 등 장소 검색을 통하여 원하는 구역에 있는 가로 쓰레기통을 알려줍니다. 대중교통이나 차량을 이용할 때, 목적지를 입력하면 해당 목적지에 있는 가로 쓰레기통을 알 수 있습니다.
>  4. 품목 별 분리수거 배출 방법 확인
>     사용자의 분리수거를 위하여 가이드를 제공합니다. 품목별로 버리는 방법을 상세하게 알려줍니다.
>  5. 상세 배출 정보 검색
>     품목 별 분리수거 가이드 외에 헷갈리는 쓰레기는 검색을 통해서 확인합니다. 분리배출 가이드 화면에서 검색을 하게 되면 품목, 구분, 배출 방법에 대한 정보가 나옵니다.

* 기대효과 및 향후 활용 분야
> 1. 현재는 서울시에 한해서만 서비스를 제공하지만 추후 전국으로 확대하여 많은 사람들이 서비스를 이용할 수 있습니다.
> 2. 일반 가로 쓰레기통 뿐만 아니라, 재활용 쓰레기통과 음식물 쓰레기통 등 여러 종류의 쓰레기통의 정보를 수집하여 지도 상에 표시할 수 있습니다.
> 3. 회원가입과 로그인 기능을 통하여 자주 가는 지역의 쓰레기통을 즐겨찾기 할 수 있습니다.
> 4. Q&A 게시판을 생성하여 사용자들의 질문 및 건의사항을 받아 서비스를 업데이트할 수 있습니다.
> 5. 길거리 쓰레기가 줄어들어 환경에 큰 도움을 줄 수 있으며, 시민의식을 향상시킬 수 있습니다.

