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
            def idName = 'topicPriorities[' + index + '].id'
            def topicIdName = 'topicPriorities[' + index +'].topicId'
            def isPreferableName = 'topicPriorities[' + index + '].isPreferable'
          
            div(class: 'topicPriorityForm row') {
              input(id: idName, name: idName, value: topicPriority.id, disabled: true, type: 'hidden')
              div(class:'input-field col s6') {
                select(id: topicIdName, name: topicIdName) {
                  option(value: '', disabled: true, 'Choose Topic')
                  subject.topics.each { topic ->
                    def optionAttrs = [value: topic.id]
                    if (topic.id == topicPriority.topicId) optionAttrs += [selected: true]
                    option(optionAttrs, topic.name)
                  }
                }
                label('Topic')
              }
              div(class: 'col s4') {
                def checkboxAttrs = [id: isPreferableName, name: isPreferableName, type: 'checkbox']
                if (topicPriority.isPreferable) checkboxAttrs += [checked: true]
                p {
                  input(checkboxAttrs)
                  label(for: isPreferableName, 'isPreferable')
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
          if (issueStandard.topicPriorities.size() == subject.topics.size()) buttonAttrs += [disabled: true]
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
            def idName = 'questionTypeOptions[' + index + '].id'
            def questionTypeIdName = 'questionTypeOptions[' + index + '].questionTypeId'
            def minQuestionsName = 'questionTypeOptions[' + index + '].minQuestions'
            def maxQuestionsName = 'questionTypeOptions[' + index + '].maxQuestions'
            def timeLimitName = 'questionTypeOptions[' + index + '].timeLimit'
          
            div(class: 'questionTypeOptionForm row') {
              input(id: idName, name: idName, disabled: 'disabled', value: questionTypeOption.id, type: 'hidden')
              div(class: 'input-field col s5') {
                select(id: questionTypeIdName, name: questionTypeIdName) {
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
                input(id: minQuestionsName, name: minQuestionsName, value: questionTypeOption.minQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: minQuestionsName, 'Minimal Number of Questions')
              }
              div(class:'input-field col s2') {
                input(id: maxQuestionsName, name: maxQuestionsName, value: questionTypeOption.maxQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: maxQuestionsName, 'Maximal Number of Questions')
              }
              div(class:'input-field col s2') {
                input(id: timeLimitName, name: timeLimitName, value: questionTypeOption.timeLimit, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: timeLimitName, 'Time Limit')
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
    if (errors && errors.hasErrors()) {
      def errorMessages = errors.getAllErrors()*.getDefaultMessage()
      div (class: 'card-panel red-text text-darken-1') {
        errorMessages.each { message ->
          span(message)
        }
      }
    }
  }
  div (hidden: true, id:'topicsHidden') {
    p (id: 'topicsListTakenSize', issueStandard.topicPriorities.size())
    div (id: 'topicSelects') {
      issueStandard.topicPriorities.eachWithIndex { topicPriority, index ->
        div (class: 'topicSelectRow') {
          p (class: 'selectId', 'topicPriorities[' + index + '].topicId')
          p (class: 'topicId', topicPriority.topicId)
        }
      }
    }
    p (id: 'topicsListTotalSize', subject.topics.size())
    div (id: 'topicsList') {
      subject.topics.each { topic ->
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
          p (class: 'selectId', 'questionTypeOptions[' + index + '].questionTypeId')
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
