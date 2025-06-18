# 숨은 사람 친구 - Backend
개발 기간 : 2025년 01월 16일(목) ~ 02월 14일(금)

## 💡프로젝트 개요
- 프로젝트명
    - 숨은 사람 친구
- 목적
    - 축제, 공연 정보 제공 및 동행자 모임 생성
- 페르소나
    - 학생 김토끼 : 좋아하는 아이돌 콘서트를 가고 싶은데, 혼자 가기에는 애매하네...
      같이 갈 사람 어디 없나?
    - 직장인 최거북이 : 다음 주 데이트때 뭘 하지? 우리 동네 축제나 공연 정보를 한눈에 찾아볼 수 있을까?

## 🙂 역할 분배
![역할 분배](https://github.com/user-attachments/assets/460fa7c7-f700-483c-8204-e7a6482bc9f2)

## ⚙️ 개발 환경
> BE Repo : [백엔드 레포 바로가기](https://github.com/BackEndSchoolPlus3th/hfBackend)   
> FE Repo : [프론트 레포 바로가기](https://github.com/BackEndSchoolPlus3th/hfFrontend)

- FE : vite(react + typescript), axios
- BE : SpringBoot, JPA, WebSocket 
- 버전 및 이슈관리 : Github
- 협업 툴 : Discord, Notion, Jira
- 서비스 배포 환경   
   - FE: vercel   
   - BE: terraform, gitAction, AWS
   - elasticsearch : ncloud, terraform
- 디자인 : Figma,cursor

## 📂 프로젝트 구조

<details>
<summary>프로젝트 구조</summary>

```
hfBackend
├─ .DS_Store
├─ Dockerfile
├─ GITCONVENTION.md
├─ README.md
├─ db_dev.trace.db
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ infraScript
│  └─ zero_downtime_deploy.py
├─ readme-photo
└─ src
   ├─ .DS_Store
   ├─ main
   │  ├─ .DS_Store
   │  ├─ generated
   │  │  └─ com
   │  │     └─ ll
   │  │        └─ hfback
   │  │           ├─ domain
   │  │           │  ├─ board
   │  │           │  │  ├─ comment
   │  │           │  │  │  └─ entity
   │  │           │  │  │     └─ QBoardComment.java
   │  │           │  │  └─ notice
   │  │           │  │     └─ entity
   │  │           │  │        └─ QBoard.java
   │  │           │  │           ...
   │  ├─ java
   │  │  └─ com
   │  │     └─ ll
   │  │        └─ hfback
   │  │           ├─ HfbackApplication.java
   │  │           ├─ domain
   │  │           │  ├─ board
   │  │           │  │  ├─ comment
   │  │           │  │  │  ├─ controller
   │  │           │  │  │  │  └─ ApiV1BoardCommentController.java
   │  │           │  │  │  ├─ dto
   │  │           │  │  │  │  └─ BoardCommentDto.java
   │  │           │  │  │  ├─ entity
   │  │           │  │  │  │  └─ BoardComment.java
   │  │           │  │  │  ├─ repository
   │  │           │  │  │  │  └─ BoardCommentRepository.java
   │  │           │  │  │  └─ service
   │  │           │  │  │     └─ BoardCommentService.java
   │  │           │  │  └─ notice
   │  │           │  │     ├─ controller
   │  │           │  │     │  └─ ApiV1BoardController.java
   │  │           │  │     ├─ dto
   │  │           │  │     │  └─ BoardDto.java
   │  │           │  │     ├─ entity
   │  │           │  │     │  └─ Board.java
   │  │           │  │     ├─ repository
   │  │           │  │     │  └─ BoardRepository.java
   │  │           │  │     └─ service
   │  │           │  │        └─ BoardService.java
   │  │           │  ├─ festival
   │  │           │  │  ├─ api
   │  │           │  │  │  ├─ scheduler
   │  │           │  │  │  │  ├─ FetchApisScheduler.java
   │  │           │  │  │  │  └─ FetchKopisScheduler.java
   │  │           │  │  │  ├─ service
   │  │           │  │  │  │  └─ ApiService.java
   │  │           │  │  │  └─ serviceImpl
   │  │           │  │  │     └─ ApiServiceImpl.java
   │  │           │  │  ├─ comment
   │  │           │  │  │  ├─ controller
   │  │           │  │  │  │  └─ ApiV1CommentController.java
   │  │           │  │  │  ├─ dto
   │  │           │  │  │  │  └─ CommentDto.java
   │  │           │  │  │  ├─ entity
   │  │           │  │  │  │  └─ Comment.java
   │  │           │  │  │  ├─ form
   │  │           │  │  │  │  ├─ AddCommentForm.java
   │  │           │  │  │  │  └─ UpdateCommentForm.java
   │  │           │  │  │  ├─ repository
   │  │           │  │  │  │  └─ CommentRepository.java
   │  │           │  │  │  ├─ service
   │  │           │  │  │  │  └─ CommentService.java
   │  │           │  │  │  └─ serviceImpl
   │  │           │  │  │     └─ CommentServiceImpl.java
   │  │           │  │  ├─ post
   │  │           │  │  │  ├─ controller
   │  │           │  │  │  │  └─ ApiV1PostController.java
   │  │           │  │  │  ├─ dto
   │  │           │  │  │  │  ├─ DetailPostDto.java
   │  │           │  │  │  │  └─ PostDto.java
   │  │           │  │  │  ├─ entity
   │  │           │  │  │  │  └─ Post.java
   │  │           │  │  │  ├─ repository
   │  │           │  │  │  │  └─ PostRepository.java
   │  │           │  │  │  ├─ service
   │  │           │  │  │  │  └─ PostService.java
   │  │           │  │  │  └─ serviceImpl
   │  │           │  │  │     └─ PostServiceImpl.java
   │  │           │  │  └─ search
   │  │           │  │     ├─ controller
   │  │           │  │     │  └─ ApiV1PostDocController.java
   │  │           │  │     ├─ document
   │  │           │  │     │  └─ MainPostDoc.java
   │  │           │  │     ├─ dto
   │  │           │  │     │  └─ ResponseFestivalSearch.java
   │  │           │  │     ├─ repository
   │  │           │  │     │  └─ PostDocRepository.java
   │  │           │  │     └─ service
   │  │           │  │        └─ PostDocService.java
   │  │           │  ├─ group
   │  │           │  │  ├─ chat
   │  │           │  │  │  ├─ chatMessageK6Test
   │  │           │  │  │  │  ├─ controller
   │  │           │  │  │  │  │  └─ ApiV1ChatMessageK6TestController.java
   │  │           │  │  │  │  └─ service
   │  │           │  │  │  │     └─ ChatMessageK6TestService.java
   │  │           │  │  │  ├─ controller
   │  │           │  │  │  │  ├─ ApiV1ChatFileController.java
   │  │           │  │  │  │  └─ ApiV1ChatMessageController.java
   │  │           │  │  │  ├─ entity
   │  │           │  │  │  │  ├─ ChatMessage.java
   │  │           │  │  │  │  └─ ChatRoomUser.java
   │  │           │  │  │  ├─ enums
   │  │           │  │  │  │  └─ ChatRoomUserStatus.java
   │  │           │  │  │  ├─ repository
   │  │           │  │  │  │  ├─ ChatMessageRepository.java
   │  │           │  │  │  │  └─ ChatRoomUserRepository.java
   │  │           │  │  │  ├─ request
   │  │           │  │  │  │  ├─ MessageReadStatusRequest.java
   │  │           │  │  │  │  ├─ MessageSearchKeywordsRequest.java
   │  │           │  │  │  │  └─ RequestMessage.java
   │  │           │  │  │  ├─ response
   │  │           │  │  │  │  ├─ ResponseMemberStatus.java
   │  │           │  │  │  │  ├─ ResponseMessage.java
   │  │           │  │  │  │  └─ ResponseMessageCount.java
   │  │           │  │  │  ├─ search
   │  │           │  │  │  │  ├─ controller
   │  │           │  │  │  │  │  └─ ApiV1ChatMessageDocController.java
   │  │           │  │  │  │  ├─ document
   │  │           │  │  │  │  │  └─ ChatMessageDoc.java
   │  │           │  │  │  │  ├─ repository
   │  │           │  │  │  │  │  └─ ChatMessageDocRepository.java
   │  │           │  │  │  │  ├─ service
   │  │           │  │  │  │  │  └─ ChatMessageDocService.java
   │  │           │  │  │  │  └─ serviceImpl
   │  │           │  │  │  │     └─ ChatMessageDocServiceImpl.java
   │  │           │  │  │  ├─ service
   │  │           │  │  │  │  ├─ ChatMessageService.java
   │  │           │  │  │  │  └─ ChatS3Service.java
   │  │           │  │  │  └─ serviceImpl
   │  │           │  │  │     ├─ ChatMessageServiceImpl.java
   │  │           │  │  │     └─ ChatS3ServiceImpl.java
   │  │           │  │  └─ chatRoom
   │  │           │  │     ├─ controller
   │  │           │  │     │  └─ ApiV1ChatRoomController.java
   │  │           │  │     ├─ converter
   │  │           │  │     │  ├─ StringDoubleListConverter.java
   │  │           │  │     │  └─ StringListConverter.java
   │  │           │  │     ├─ dto
   │  │           │  │     │  ├─ ChatRoomDto.java
   │  │           │  │     │  └─ DetailChatRoomDto.java
   │  │           │  │     ├─ entity
   │  │           │  │     │  └─ ChatRoom.java
   │  │           │  │     ├─ form
   │  │           │  │     │  ├─ CreateChatRoomForm.java
   │  │           │  │     │  └─ UpdateChatRoomForm.java
   │  │           │  │     ├─ repository
   │  │           │  │     │  └─ ChatRoomRepository.java
   │  │           │  │     ├─ service
   │  │           │  │     │  └─ ChatRoomService.java
   │  │           │  │     └─ serviceImpl
   │  │           │  │        └─ ChatRoomServiceImpl.java
   │  │           │  ├─ home
   │  │           │  │  └─ main
   │  │           │  │     └─ controller
   │  │           │  │        └─ MainController.java
   │  │           │  └─ member
   │  │           │     ├─ alert
   │  │           │     │  ├─ controller
   │  │           │     │  │  └─ ApiV1AlertController.java
   │  │           │     │  ├─ dto
   │  │           │     │  │  ├─ AlertRequest.java
   │  │           │     │  │  └─ AlertResponse.java
   │  │           │     │  ├─ entity
   │  │           │     │  │  └─ Alert.java
   │  │           │     │  ├─ enums
   │  │           │     │  │  ├─ AlertType.java
   │  │           │     │  │  ├─ BoardAlertType.java
   │  │           │     │     ├─ ...
   │  │           │     │  ├─ events
   │  │           │     │  │  ├─ AlertEvent.java
   │  │           │     │  │  ├─ AlertEventHandler.java
   │  │           │     │     ├─ ...
   │  │           │     │  ├─ repository
   │  │           │     │  │  └─ AlertRepository.java
   │  │           │     │  └─ service
   │  │           │     │     ├─ AlertEventPublisher.java
   │  │           │     │     └─ AlertService.java
   │  │           │     ├─ auth
   │  │           │     │  ├─ config
   │  │           │     │  │  ├─ AsyncConfig.java
   │  │           │     │  │  ├─ EmailConfig.java
   │  │           │     │  │  └─ SmsConfig.java
   │  │           │     │  ├─ controller
   │  │           │     │  │  └─ ApiV1AuthController.java
   │  │           │     │  ├─ dto
   │  │           │     │  │  ├─ EmailInfo.java
   │  │           │     │  │  ├─ EmailRequest.java
   │  │           │     │  │  ├─ EmailVerifyRequest.java
   │  │           │     │     ├─ ...
   │  │           │     │  ├─ entity
   │  │           │     │  │  └─ SocialAccount.java
   │  │           │     │  ├─ repository
   │  │           │     │  │  └─ SocialAccountRepository.java
   │  │           │     │  └─ service
   │  │           │     │     ├─ AuthService.java
   │  │           │     │     ├─ AuthTokenService.java
   │  │           │     │     ├─ ...
   │  │           │     ├─ member
   │  │           │     │  ├─ controller
   │  │           │     │  │  ├─ ApiV1FriendController.java
   │  │           │     │  │  └─ ApiV1MemberController.java
   │  │           │     │  ├─ dto
   │  │           │     │  │  ├─ AddPasswordRequest.java
   │  │           │     │  │  ├─ AddressResponse.java
   │  │           │     │  │  ├─ ...
   │  │           │     │  ├─ entity
   │  │           │     │  │  ├─ Friend.java
   │  │           │     │  │  └─ Member.java
   │  │           │     │  ├─ repository
   │  │           │     │  │  ├─ FriendRepository.java
   │  │           │     │  │  └─ MemberRepository.java
   │  │           │     │  └─ service
   │  │           │     │     ├─ FriendService.java
   │  │           │     │     ├─ MemberService.java
   │  │           │     │     ├─ ...
   │  │           │     └─ report
   │  │           │        ├─ controller
   │  │           │        │  └─ ApiV1ReportController.java
   │  │           │        ├─ dto
   │  │           │        │  ├─ ReportListResponse.java
   │  │           │        │  ├─ ReportRequest.java
   │  │           │        │  ├─ ...
   │  │           │        ├─ entity
   │  │           │        │  └─ Report.java
   │  │           │        ├─ repository
   │  │           │        │  └─ ReportRepository.java
   │  │           │        └─ service
   │  │           │           └─ ReportService.java
   │  │           ├─ global
   │  │           │  ├─ app
   │  │           │  │  └─ AppConfig.java
   │  │           │  ├─ aspect
   │  │           │  │  └─ ResponseAspect.java
   │  │           │  ├─ chatS3
   │  │           │  │  └─ ChatS3Config.java
   │  │           │  ├─ exceptions
   │  │           │  │  ├─ ErrorCode.java
   │  │           │  │  ├─ GlobalExceptionHandler.java
   │  │           │  │  └─ ServiceException.java
   │  │           │  ├─ initData
   │  │           │  │  └─ NotProd.java
   │  │           │  ├─ jpa
   │  │           │  │  └─ BaseEntity.java
   │  │           │  ├─ oauth2
   │  │           │  │  ├─ CustomAuthorizationRequestResolver.java
   │  │           │  │  ├─ ...
   │  │           │  ├─ redis
   │  │           │  │  └─ RedisConfig.java
   │  │           │  ├─ rq
   │  │           │  │  └─ Rq.java
   │  │           │  ├─ rsData
   │  │           │  │  └─ RsData.java
   │  │           │  ├─ security
   │  │           │  │  ├─ ApiSecurityConfig.java
   │  │           │  │  ├─ CustomAuthenticationFilter.java
   │  │           │  │  ├─ ...
   │  │           │  ├─ storage
   │  │           │  │  ├─ FileStorageHandler.java
   │  │           │  │  ├─ FileUploadRequest.java
   │  │           │  │  ├─ ...
   │  │           │  ├─ web
   │  │           │  │  └─ WebConfig.java
   │  │           │  ├─ webMvc
   │  │           │  │  ├─ CustomWebMvcConfig.java
   │  │           │  │  ├─ LoginUser.java
   │  │           │  │  └─ LoginUserArgumentResolver.java
   │  │           │  └─ websocket
   │  │           │     ├─ ChatMessageHandler.java
   │  │           │     └─ WebSocketConfig.java
   │  │           └─ standard
   │  │              ├─ base
   │  │              │  └─ Empty.java
   │  │              └─ util
   │  │                 └─ Ut.java
   │  └─ resources
   │     ├─ application-dev.yml
   │     ├─ application-prod.yml
   │     ├─ ...
   │     ├─ elasticsearch
   │     │  ├─ mappings.json
   │     │  └─ settings.json
   │     ├─ static
   │     └─ templates
   └─ test
   
```
</details>

## 📋 ERD

![erd](https://github.com/user-attachments/assets/a93e37f1-84aa-4c1c-abfe-849775c61b7d)

## 📄 API 명세서

![명세](https://github.com/user-attachments/assets/bfec13ae-199b-4880-9e1d-5edd58eeaaa3)

## 📄 GitConvention
GitConvention에 시간을 너무 쏟지 않도록 모두에게 익숙한 깃컨벤션 채택   
```
main
├─ dev
│  └─ 개인 브랜치
```

## 🔧 기술 스택
![기술스택](https://github.com/user-attachments/assets/6f3419e2-5620-443e-b6fd-312e2c783874)
### FE
React + TypeScript
   - 프론트 서버를 분리하면서 힘을 최대한 덜 들이기 위해 가장 유명하고 익숙한 **리액트**를 선정.
   - 자바스크립트의 변수 데이터 타입이 동적이기 때문에 추후 찾지 못할 타입 에러를 방지해 **타입 스크립트** 추가
   - 간편한 API 요청을 위해 **axios** 사용

### BE
SpringBoot + JPA + QueryDSL
   - 빠른 프로젝트 시작을 위해 WAS가 내장되어있는 **SpringBoot** 선정
   - 데이터 매핑 자동화를 위한 **JPA** 도입 및 **QueryDSL** 사용

WebSocket STOMP
   - 채팅 기능 구현을 위한 **WebSocket STOMP** 라이브러리 사용

JWT
   - 회원 정보 토큰 인증, 인가 사용을 위한 **JWT** 채택

### DBMS
Mysql, Redis
   - 짧은 개발 기간을 고려하여 모두에게 익숙한 **Mysql** 채택
   - 이메일, 휴대폰 인증 여부 확인을 위한 **Redis** 채택

### elasticsearch
   - 축제, 채팅 데이터의 빠른 검색을 위한 **elasticsearch** 도입

## 🔧 아키텍쳐

![아키텍처](https://github.com/user-attachments/assets/f18aae8b-605b-43a2-9f55-8b80abe945fa)
![cicd파이프라인](https://github.com/user-attachments/assets/4b7a95f4-155d-4210-9936-e9e989c46019)

## 💁‍♂ 구현 기능

### 알림
- 친구 추가, 채팅방, 공지사항 등 알림 기능

### 로그인 및 마이페이지 기능
- 자체 회원가입, 자체 로그인 기능
- 소셜 로그인 기능
- 마이페이지
   - 회원 프로필 수정 기능
   - 소셜 계정 연동 기능
   - 친구 추가 기능

### 축제 게시글 댓글&답글 및 모임(채팅방) 기능
- 메일 3시 스케줄러를 통한 축제/공연 API 정보 파싱
- 축제/공연 게시글 및 댓글과 답글 기능
- 축제/공연 게시글에 모임(채팅방) 생성 기능
- 모임(채팅방) 관리 기능
   - 방장 권한 위임
   - 참여 수락 및 참여자 강퇴 기능
 
### 실시간 채팅 (WebSocket STOMP + REST API)
- WebSocket + STOMP를 사용한 실시간 채팅 송/수신
- 메시지를 읽지 않은 사람 수 표시 (카카오톡의 1)
- 채팅방 멤버 온라인/오프라인 상태 표시
- AWS S3를 사용한 이미지 업로드, 삭제 기능 구현 (Gif, Png, Jpg, Jpeg)

### 검색 기능 (V1: MySQL, V2: Elasticsearch)
채팅
- V1 : 동적 쿼리를 사용한 채팅방 내 메시지 검색 기능 (QueryDSL)
- V2 : Elasticsearch를 이용한 채팅방 내 메시지 검색 기능 

축제/공연
- V1 : 동적 쿼리를 사용한 축제/공연 키워드, 장르 검색 기능 (QueryDSL)
- V2 : Elasticsearch를 이용한
   - 축제/공연 키워드, 장르 검색 기능
   - 날짜나 사용자 지역에 맞춘 메인 배너 기능

### 관리자 페이지 및 공지사항 기능
- 관리자 권한을 가진 사용자만 진입할 수 있는 관리자 페이지
   - 회원 조회 기능
   - 모임 조회 및 삭제 기능
   - 공지사항 작성 기능
- 관리자가 작성한 공지사항 조회 및 댓글 작성 기능

## 🎞️ 시연 영상
#### 배너, 축제/공연 검색
![시연2](https://github.com/user-attachments/assets/1a945d90-df07-43e1-b7b3-9e987961e206)


