layout 'layout/main.tpl',
title:  'View Issue Standard',
_csrf: _csrf,
content: contents {
  div(class: 'row') {
    a (class: 'btn-floating btn-large waves-effect waves-light right blue', onclick:'location.href="/personal/issue_standard/update/' + issueStandard.id + '"') {
      i (class: 'material-icons', 'edit')
    }
    h4 (class: 'header', 'Issue Standard')
    table(class: 'striped') {
      thead {
        tr {
          th('#')
          th('Time Limit')
          th('Number of Questions')
        }
      }
      tbody {
        tr {
          td(1)
          td(issueStandard.timeLimit)
          td(issueStandard.questionsNumber)
        }
      }
    }
  }
  newLine()
  div(class: 'row') {
    h5(class: 'header', 'Topics')
    table(class: 'striped') {
      thead {
        tr {
          th('#')
          th('Topic Name')
          th('Priority')
        }
      }
      tbody {
        for (int i = 0; i < topicPriorities.size(); i++) {
          tr {
            td(i + 1)
            td(topicPriorities[i].topic.name)
            td(topicPriorities[i].isPreferable ? 'Preferable': 'Non-preferable')
          }
        }
      }
    }
  }
  newLine()
  div(class: 'row') {
    h5(class: 'header', 'Question Type Options')
    table(class: 'striped') {
      thead {
        tr {
          th('#')
          th('Question Type Name')
          th('Min number of questions')
          th('Max number of questions')
          th('Time Limit')
        }
      }
      tbody {
        for (int i = 0; i < questionTypeOptions.size(); i++) {
          tr {
            td(i + 1)
            td(questionTypeOptions[i].questionType.name)
            td(questionTypeOptions[i].minQuestions ? questionTypeOptions[i].minQuestions : 'No Restriction')
            td(questionTypeOptions[i].maxQuestions ? questionTypeOptions[i].maxQuestions : 'No Restriction')
            td(questionTypeOptions[i].timeLimit ? questionTypeOptions[i].timeLimit : 'No Restriction')
          }
        }
      }
    }
  }
  newLine()
}