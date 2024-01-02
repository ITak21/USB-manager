


# USB-manager
> 해당 프로젝트는 spring framework로 개발하였습니다.

## 제작기간
>2023.08.24 ~ 2023.12.28 (5개월)

## 제작의도
> 전산실에서 근로장학생으로 일하면서 소프트웨어 대여 업무를 맡았습니다.
>  
> 소프트웨어는 주로 USB에 설치파일이 담겨져있으며 이를 대여해주는 과정에서
>   
> 어느 USB에 어떤 프로그램이 담겨져있는지 구분할 필요가 있었고
>  
> 매번 종이에 수기로 신청서를 작성해야하는 번거로움과 종이 사용 절감을 위해
>   
> 이 과정을 프로그램화 해보기로 했습니다.

## 주요기능

### USB 내에 파일 확인 및 추가/수정
- usb 내에 프로그램들을 확인할 수 있으며 그 목록을 추가 및 수정이 가능하다.

### USB 대여 및 반납
- usb를 대여하고 반납할 수 있다.

### 대여중인 usb 식별
- 현재 대여 중인 usb를 확인할 수 있으며 누가 언제 대여해갔는지 확인할 수 있다.

### 프로그램 검색
- 원하는 프로그램이 어느 usb에 있는지 확인할 수 있다.

### 대여 및 반납시 로그테이블에 기록
- 대여 및 반납시 로그테이블에 대여자, 학과, 대여날짜, 반납날짜가 기록된다.

## DB ERD
<img width="700" src=https://github.com/ITak21/USB-manager/assets/118645678/d1687660-6948-40e8-bc97-4d1fa5c76101>

- 현재 USB가 대여중인지 확인할 수 있다.
- 현재 어떤 프로그램이 존재하는지 알 수 있다.
- 어떤 프로그램이 어느 USB에 들어있는지 알 수 있다.
- 대여시 로그테이블에 대여자, 대여 학과/부서, 대여 날짜, 반납 날짜가 기록된다.

## 화면 구성 📺
| 메인 페이지(초기 화면)  |  USB추가 페이지   |
| :-------------------------------------------: | :------------: |
| <img width="329" src="images/usb-manager 초기화면.jpg">|  <img width="329" src="images/usb-manager usb추가화면.jpg">|  
| 파일추가 페이지  |   파일추가 후 메인 페이지   |  
| <img width="329" src="images/usb-manager 파일추가화면.jpg">| <img width="329" src="images/usb-manager 파일추가된화면.jpg">|
| 대여 페이지  |  대여 후 페이지  |
| <img width="329" src="images/usb-manager 대여화면.jpg">| <img width="329" src="images/usb-manager 대여후화면.jpg">|
| 대여확인 페이지 | 검색 페이지  |
| <img width="329" src="images/usb-manager 대여확인화면.jpg">| <img width="329" src="images/usb-manager 검색화면.jpg">|
| USB에 포함된 프로그램 조회 페이지|
| <img width="329" src="images/usb-manager usb에 포함된 프로그램들.jpg">|
