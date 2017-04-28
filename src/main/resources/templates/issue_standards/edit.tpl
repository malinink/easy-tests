layout 'layout/main.tpl',
title:  'Edit Issue Standard',
_csrf: _csrf,
content: contents {
  div(class: 'row') {
    h4(class: 'header', 'Issue Standard for subject: ' + subject.name)
    form(id: 'issueStandardForm', method:'post', class: 'col s12') {
      input (id: '_csrf', type:'hidden', name:_csrf.parameterName, value:_csrf.token)
      div(class: 'row') {
        input(id: 'id', name: 'id', value: issueStandard.id, disabled: true, type: 'hidden')
        div(class: 'row') {
          div(class: 'input-field col s4') {
            input(id: 'timeLimit', name: 'timeLimit', value: issueStandard.timeLimit,  placeholder: 'No Restriction', type: 'number', min: 1, class:'validate')
            label(class: 'active', for:'timeLimit', 'Time Limit')
          }
        }
        div(class: 'row') {
          div(class: 'input-field col s4') {
            input(id: 'questionsNumber', name: 'questionsNumber', value: issueStandard.questionsNumber, placeholder: 'No Restriction', type: 'number', min: 1, class:'validate')
            label(class: 'active', for:'questionsNumber', 'Number of Questions')
          }
        }
        input(id: 'subjectId', name: 'subjectId', value: issueStandard.subjectId, disabled: true, type: 'hidden')
      }
      newLine()
      div(class: 'row') {
        h5(class: 'header', 'Topic Priorities')
        div(id: 'topicPrioritiesBlock') {
          issueStandard.topicPriorities.eachWithIndex { topicPriority, index ->
          
            div(class: 'topicPriorityForm row') {
              input(id: 'tpId' + index, name: 'tpId' + index, value: topicPriority.id, disabled: true, type: 'hidden')
              div(class:'input-field col s6') {
                select(id:'tpTopicId' + index, name:'tpTopicId' + index) {
                  option(value: '', disabled: true, 'Choose Topic')
                  topics.each { topic ->
                    def optionAttrs = [value: topic.id]
                    if (topic.id == topicPriority.topicId) optionAttrs += [selected: true]
                    option(optionAttrs, topic.name)
                  }
                }
                label('Topic')
              }
              div(class: 'col s4') {
                def checkboxAttrs = [id: 'tpIsPreferable' + index, name: 'tpIsPreferable' + index, type: 'checkbox']
                if (topicPriority.isPreferable) checkboxAttrs += [checked: true]
                p {
                  input(checkboxAttrs)
                  label(for: 'tpIsPreferable' + index, 'isPreferable')
                }
              }
              a (class: 'deleteTopicPriorityForm btn-floating btn-large waves-effect waves-light right blue-grey') {
                i (class: 'material-icons', 'delete')
                yield 'Delete'
              }
            }
          }
        }
        div(class: 'row') {
          def buttonAttrs = [id: 'addTopicPriorityForm', class: 'btn-floating btn-large waves-effect waves-light right blue']
          if (issueStandard.topicPriorities.size() == topics.size()) buttonAttrs += [disabled: true]
          a(buttonAttrs) {
            i(class: 'material-icons', 'add')
            yield 'Add'
          }
        } 
      }
      newLine()
      div(class: 'row') {
        h5(class: 'header', 'Question Types Options')
        div(id: 'questionTypeOptionsBlock') {
          issueStandard.questionTypeOptions.eachWithIndex { questionTypeOption, index ->
          
            div(class: 'questionTypeOptionForm row') {
              input(id: 'qtoId' + index, name: 'qtoId' + index, disabled: 'disabled', value: questionTypeOption.id, type: 'hidden')
              div(class: 'input-field col s5') {
                select(id: 'qtoQuestionTypeId' + index, name: 'qtoQuestionTypeId' + index) {
                  option(value:'', disabled: true, 'Choose Question Type')
                  questionTypes.each { questionType ->
                    def optionAttrs = [value: questionType.id]
                    if (questionType.id == questionTypeOption.questionTypeId) optionAttrs += [selected: true]
                    option(optionAttrs, questionType.name)
                  }
                }
                label('Question Type')
              }
              div(class:'input-field col s2') {
                input(id: 'qtoMinQuestions' + index, name: 'qtoMinQuestions' + index, value: questionTypeOption.minQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: 'qtoMinQuestions' + index, 'Minimal Number of Questions')
              }
              div(class:'input-field col s2') {
                input(id: 'qtoMaxQuestions' + index, name: 'qtoMaxQuestions' + index, value: questionTypeOption.maxQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: 'qtoMaxQuestions' + index, 'Maximal Number of Questions')
              }
              div(class:'input-field col s2') {
                input(id: 'qtoTimeLimit'+ index, name: 'qtoTimeLimit' + index, value: questionTypeOption.timeLimit, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: 'qtoTimeLimit' + index, 'Time Limit')
              }
              a (class: 'deleteQuestionTypeOptionForm btn-floating btn-large waves-effect waves-light right blue-grey') {
                i (class: 'material-icons', 'delete')
                yield 'Delete'
              }
            }
          }
        }
        div(class: 'row') {
          def buttonAttrs = [id: 'addQuestionTypeOptionForm', class: 'btn-floating btn-large waves-effect waves-light right blue']
          if (issueStandard.questionTypeOptions.size() == questionTypes.size()) buttonAttrs += [disabled: true]
          a(buttonAttrs) {
            i(class: 'material-icons', 'add')
            yield 'Add'
          }
        } 
      }
      div(class: 'row') {
        button(type: 'submit', class: 'btn-large waves-effect waves-light blue', name: 'save') {
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
  div (hidden: true, id:'topicsHidden') {
    p (id: 'topicsListTakenSize', issueStandard.topicPriorities.size())
    div (id: 'topicSelects') {
      issueStandard.topicPriorities.eachWithIndex { topicPriority, index ->
        div (class: 'topicSelectRow') {
          p (class: 'selectId', 'tpTopicId' + index)
          p (class: 'topicId', topicPriority.topicId)
        }
      }
    }
    p (id: 'topicsListTotalSize', topics.size())
    div (id: 'topicsList') {
      topics.each { topic ->
        div (class: 'topicsListRow') {
          p (class: 'id', topic.id)
          p (class: 'name', topic.name)
        }
      }
    }
  }
 div (hidden: true, id:'questionTypesHidden') {
    p (id: 'questionTypesListTakenSize', issueStandard.questionTypeOptions.size())
    div (id: 'questionTypeSelects') {
      issueStandard.questionTypeOptions.eachWithIndex { questionTypeOption, index ->
        div (class: 'questionTypeSelectRow') {
          p (class: 'selectId', 'qtoQuestionTypeId' + index)
          p (class: 'questionTypeId', questionTypeOption.questionTypeId)
        }
      }
    }
    p (id: 'questionTypesListTotalSize', questionTypes.size())
    div (id: 'questionTypesList') {
      questionTypes.each { questionType ->
        div (class: 'questionTypesListRow') {
          p (class: 'id', questionType.id)
          p (class: 'name', questionType.name)
        }
      }
    }
  }
}
script(type:'text/javascript', src:'/js/issue_standard_edit.js') {}
