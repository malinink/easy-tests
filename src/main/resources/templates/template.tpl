<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

  <div th:repalace="~{head}">
  </div>

  <body>

    <div th:replace="~{main-header}">
    </div>

    <div th:replace="~{main}">
    </div>

    <div th:replace="~{main-footer :: copy}">
    </div>

  </body>


</html>