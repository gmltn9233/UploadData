## 플로우 차트

![image](https://github.com/gmltn9233/UploadData/assets/63588364/d325e480-0647-420c-811d-61f1a59280d6)


## DB 설계
* 회원 테이블 (Student) 은 학번(PK)을 가지며, 비밀번호, 이름, 소속 학과, 이메일(비밀번호 변경용도)의 정보를 가진다.
* 과목 테이블 (Subject) 은 학수번호(PK)을 가지며, 과목명, 학점, 설계학점의 정보를 가진다.
* 이수과목 테이블 (Completed_Subject)은 학번(PK, 회원 테이블의 학번을 참조하는 외래키), 수강년도, 학기, 학수번호(과목 테이블의 학수번호를 참조하는 외래키)의 정보를 가진다.
* 모든 칼럼에는 NULL 값이 허용되지 않는다.

## E-R 다이어그램
![image](https://github.com/gmltn9233/UploadData/assets/63588364/e87138e3-9174-4be4-b979-c863bbcff23f)
