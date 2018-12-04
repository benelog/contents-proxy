## 기술 스택
- Java 11
- Web tier : Spring Boot (+ Spring Web MVC)
- Perstence layer : JPA (Hibernate)
- github 연동 : [egit-github](https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core)
- markdown -> html 렌더링
    - 서버에서 할 경우 : [flexmark-java](https://github.com/vsch/flexmark-java)
    - 클라이언트에서 할 경우 : [markdown-it](https://github.com/markdown-it/markdown-it)
- DB : H2db

## H2 DB 관리
- 접속 정보는 [application.property](src/main/resources/application.properties) 를 참조한다.
- 명령행에서 `mvn exec:java`로 DB관리도구를 실행할 수 있다.
- 웹서버가 떠 있는 상태에서는 http://localhost:8080/h2-console 로 DB 관리도구로 연결할 수 있다.
- 별도의 DB client를 사용한다면 [DBeaver](https://dbeaver.io)를 추천한다.
- DB 스키마를 모두 지우고 싶다면 `rm -rf ~/hackday`를 명령행에서 실행한다.

## 코딩 컨벤션
- 모든 소스, 텍스트 문서 파일의 인코딩은 UTF-8로 통일한다.
- 새줄 문자는 LF(\n)로 한다.

### .java 파일
- 탭(tab) 문자를 사용하여 들여쓴다. 탭 대신 스페이스를 사용하지 않는다.
- 일반적인 Java의 컨벤션을 바탕으로 한다.
![java-convention](http://pds21.egloos.com/pds/201508/13/79/a0274579_55cbfda73d78d.gif)

### IDE 설정 & 팁
IntelliJ를 기준으로 아래와 같이 설정한다.

#### Line separator 설정
- `File` > `Settings` > `Editor` > `Code Style` 메뉴로 이동한다.
- `General` 탭에서
	- `Line separator` : `Unix and OS X(\n)` 으로 설정

#### 탭설정
- `File` > `Settings` > `Editor` > `Code Style` > `Java` 메뉴로 이동한다.
- `Tabs and Indents` 탭에서
	- `Use tab charactor` : 체크
	- `Tab size` : 4

#### 화이트 스페이스 보이게 하기
탭과 스페이스를 IDE 안에서 쉽게 구분이 가능하도록 설정한다.

- `File` > `Settings` > `Editor` > `General` > `Appearance` 메뉴로 이동한다.
- `Show whitespaces`를 선택한다. 하위 분류에서 `Leading, Inner`,`Trailing`를 모두 선택한다.

#### 핸들바 템플릿 수정
- 수정 후 `Ctrl + F9`를 눌러야 서버를 띄운 상태에서 재시작없이 바로 반영된다.

## Commit 로그 규칙
- 포멧 : `{prefix}: {변경 내용} (#{이슈번호})`
- 예시 : `feat: Dropbox 컨텐츠 연동 (#130)`
- prefix
	- 기능 추가 : `feat:`
	- 오류 수정 : `fix:`
	- 잡다한 일 : `chore`
	- 문서화 : `doc:`
	- 리팩토링 : `refactor:`

## 결과
#### 메인
post 목록이 나온다. 각 post 별로 제목, 조회수, 생성 날짜를 확인할 수 있다.

<img width="559" alt="2018-12-04 9 01 41" src="https://user-images.githubusercontent.com/19392136/49441043-b67ef300-f808-11e8-9f6a-8ce6c4c809d5.png">

#### post 추가
post 를 추가한다. title, github url 을 입력한다. (단, github url 은 master branch 만 입력한다.)

<img width="559" alt="2018-12-04 9 01 23" src="https://user-images.githubusercontent.com/19392136/49441061-be3e9780-f808-11e8-91aa-897b19f9f9dd.png">

#### post 상세
post 제목, 조회수, 내용(github 에서 수집한 내용), 생성날짜를 확인할 수 있다.

<img width="559" alt="2018-12-04 8 59 10" src="https://user-images.githubusercontent.com/19392136/49441069-c26ab500-f808-11e8-9482-609e36a03de7.png">

#### 블로그 설정
블로그의 제목을 수정한다.

<img width="559" alt="2018-12-04 9 00 36" src="https://user-images.githubusercontent.com/19392136/49441066-c0085b00-f808-11e8-9c87-401e6d5a18ad.png">

#### Sequence Diagram
<img width="758" alt="2018-12-01 4 36 44" src="https://user-images.githubusercontent.com/19392136/49325615-ff287900-f588-11e8-93ee-00020803e639.png">
