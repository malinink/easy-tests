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
        issueStandard.topicPriorities.eachWithIndex { topicPriority, index ->
          tr {
            td(index + 1)
            td(topicPriority.topic.name)
            td(topicPriority.isPreferable ? 'Preferable': 'Non-preferable')
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
        issueStandard.questionTypeOptions.eachWithIndex { questionTypeOption, index ->
          tr {
            td(index + 1)
            td(questionTypeOption.questionType.name)
            td(questionTypeOption.minQuestions ?: 'No Restriction')
            td(questionTypeOption.maxQuestions ?: 'No Restriction')
            td(questionTypeOption.timeLimit ?: 'No Restriction')
          }
        }
      }
    }
  }
  newLine()
}
