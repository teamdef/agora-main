# agora-main

## CI/CD
### Docker Image Management
(임시) 특정 dev 커밋에 버전을 태깅하여, 동일한 태그로 docker hub에 push
- 작업 완료 후 develop branch 에 tag 를 달아 커밋 푸시
  - tag 는 `vx.x.n` 이며, develop branch 에서는 n 을 수정
  - 숫자는 이전 버전보다 높기만 하면 괜찮을 듯?
  ```
  git tag v0.1.0 // 특정 커밋에 태그 생성
  git push origin tag v0.1.0 // 태그를 remote 에 push
  ```
- docker image build 및 push
  
  ```
  ./gradlew build
  docker build . --tag teamdef/agora-back-main:0.1.0
  docker push teamdef/agora-back-main:0.1.0
  ```

  - latest 로 추가하려면, 위 커맨드 이후 아래 추가 실행 
    ```
    docker build . --tag teamdef/agora-back-main:latest
    docker push teamdef/agora-back-main:latest
    ```