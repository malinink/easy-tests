layout 'layout/main.tpl',
title:  'Edit Issue Standard',
_csrf: _csrf,
content: contents {
  div(class: 'row') {
    h4(class: 'header', 'Issue Standard for subject: ' + issueStandard.subject.name)
    form(class: 'col s12') {
      div(class: 'row') {
        input(id: 'id', disabled: 'true', value: issueStandard.id, type: 'hidden')
        div(class: 'row') {
          div(class: 'input-field col s4') {
            input(id: 'timeLimit', value: issueStandard.timeLimit,  placeholder: 'No Restriction', type: 'number', min: 1, class:'validate')
            label(class: 'active', for:'timeLimit', 'Time Limit')
          }
        }
        div(class: 'row') {
          div(class: 'input-field col s4') {
            input(id: 'questionsNumber', value: issueStandard.questionsNumber, placeholder: 'No Restriction', type: 'number', min: 1, class:'validate')
            label(class: 'active', for:'questionsNumber', 'Number of Questions')
          }
        }
        input(id: 'subjectId', disabled: 'true', value: issueStandard.subject.id, type: 'hidden')
      }
      newLine()
      div(class: 'row') {
        h5(class: 'header', 'Topic Priorities')
        for (int i = 0; i < topicPriorities.size(); i++) {
          div(class: 'row') {
            input(id: 'topicPriority' + i, disabled: 'true', value: topicPriorities[i].id, type: 'hidden')
            div(class:'input-field col s6') {
              select {
                option(value: '', disabled, selected, 'Choose Topic')
                topics.each {topic ->
                  option(value: topic.id, topic.name)
                }
              }
              label('Topic ' + i)
            }
            div(class: 'col s4') {
              p {
                input(id: 'isPreferable' + i, type: 'checkbox', checked: topicPriorities[i].isPreferable)
                label(for: 'isPreferable' + i, 'isPreferable')
              }
            }
            a (class: 'btn-floating btn-large waves-effect waves-light right blue-grey', onclick:'') {
              //i (class: 'material-icons', 'delete')
              yield 'Delete'
            }
          }
        }
        div(class: 'row') {
          a(class: 'btn-floating btn-large waves-effect waves-light right blue') {
            i(class: 'material-icons', 'add')
            yield 'Add'
          }
        } 
      }
      newLine()
      div(class: 'row') {
        h5(class: 'header', 'Question Types Options')
        for (int i = 0; i < questionTypeOptions.size(); i++) {
          div(id: 'questionTypeOption' + i, class: 'row') {
            input(id: 'id' + i, disabled: 'true', value: questionTypeOptions[i].id, type: 'hidden')
            div(id: 'questionTypeId' + i, class: 'input-field col s5') {
              select {
                option(value:'', disabled, selected, 'Choose Question Type')
                questionTypes.each { questionType ->
                  option(value: questionType.id, questionType.name)
                }
              }
              label('Question Type ' + i)
            }
            div(class:'input-field col s2') {
              input(id: 'minQuestions'+ i, value: questionTypeOptions[i].minQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
              label(class: 'active', for: 'minQuestions' + i, 'Minimal Number of Questions')
            }
            div(class:'input-field col s2') {
              input(id: 'maxQuestions'+ i, value: questionTypeOptions[i].maxQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
              label(class: 'active', for: 'maxQuestions' + i, 'Maximal Number of Questions')
            }
            div(class:'input-field col s2') {
              input(id: 'timeLimit'+ i, value: questionTypeOptions[i].timeLimit, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
              label(class: 'active', for: 'timeLimit' + i, 'Time Limit')
            }
            a (class: 'btn-floating btn-large waves-effect waves-light right blue-grey', onclick:'') {
              //i (class: 'material-icons', 'delete')
              yield 'Delete'
            }
          }
        }
        div(class: 'row') {
          a(class: 'btn-floating btn-large waves-effect waves-light right blue') {
            i(class: 'material-icons', 'add')
            yield 'Add'
          }
        } 
      }
      div(class: 'row') {
        a(class: 'btn-large waves-effect waves-light blue', type: 'submit', method: 'post', action: '/personal/update/' + issueStandard.id, name: 'save') {
          i(class: 'material-icons right', 'send')
          yield 'Save'
        }
        a(class: 'btn-large waves-effect waves-light red', onclick:'location.href="/personal/issue_standard/' + issueStandard.id + '"', name: 'cancel') {
          i (class:'material-icons right', 'cancel')
          yield 'Cancel'
        }
      }
    }
  }
}