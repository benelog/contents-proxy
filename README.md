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
- 새줄 문자는 LF로 한다.

### .java 파일
- 탭(tab) 문자를 사용하여 들여쓴다. 탭 대신 스페이스를 사용하지 않는다.

### IDE 설정
IntelliJ를 기준으로 아래와 같이 설정한다.

#### 핸들바 템플릿 수정
- 수정 후 `Ctrl + F9`를 눌러야 서버를 띄운 상태에서 재시작없이 바로 반영된다.

#### Line separator 설정
- `File` > `Settings` > `Editor` > `Code Style` 메뉴로 이동한다.
- `General` 탭에서
	- `Line separator` : `Unix and OS X(\n)` 으로 설정

#### 탭설정
- `File` > `Settings` > `Editor` > `Code Style` > `Java` 메뉴로 이동한다.
- `Tabs and Indents` 탭에서
	- `Use tab charactor` : 선택
	- `Tab size` : 4
