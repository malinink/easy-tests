layout 'layout/main.tpl',
title:  'Edit Issue Standard',
_csrf: _csrf,
content: contents {
  div(class: 'row') {
    h4(class: 'header', 'Issue Standard for subject: ' + subject.name)
    form(id: 'issueStandardForm', method:'post', class: 'col s12') {
      input (id: '_csrf', type:'hidden', name:_csrf.parameterName, value:_csrf.token)
      div(class: 'row') {
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
            def idId = 'topicPriorities_id_' + index
            def topicIdId = 'topicPriorities_topicId_' + index
            def isPreferableId = 'topicPriorities_isPreferable_' + index
            
            def idName = 'topicPriorities[' + index + '].id'
            def topicIdName = 'topicPriorities[' + index +'].topicId'
            def isPreferableName = 'topicPriorities[' + index + '].isPreferable'
          
            div(class: 'topicPriorityForm row') {
              input(id: idId, name: idName, value: topicPriority.id, disabled: true, type: 'hidden')
              div(class:'input-field col s6') {
                select(id: topicIdId, name: topicIdName) {
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
                def checkboxAttrs = [id: isPreferableId, name: isPreferableName, type: 'checkbox']
                if (topicPriority.isPreferable) checkboxAttrs += [checked: true]
                p {
                  input(checkboxAttrs)
                  label(for: isPreferableId, 'isPreferable')
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
            def idId = 'questionTypeOptions_id_' + index
            def questionTypeIdId = 'questionTypeOptions_questionTypeId_' + index
            def minQuestionsId = 'questionTypeOptions_minQuestions_' + index
            def maxQuestionsId = 'questionTypeOptions_maxQuestions_' + index
            def timeLimitId = 'questionTypeOptions_timeLimit_' + index
            
            def idName = 'questionTypeOptions[' + index + '].id'
            def questionTypeIdName = 'questionTypeOptions[' + index + '].questionTypeId'
            def minQuestionsName = 'questionTypeOptions[' + index + '].minQuestions'
            def maxQuestionsName = 'questionTypeOptions[' + index + '].maxQuestions'
            def timeLimitName = 'questionTypeOptions[' + index + '].timeLimit'
          
            div(class: 'questionTypeOptionForm row') {
              input(id: idId, name: idName, disabled: 'disabled', value: questionTypeOption.id, type: 'hidden')
              div(class: 'input-field col s5') {
                select(id: questionTypeIdId, name: questionTypeIdName) {
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
                input(id: minQuestionsId, name: minQuestionsName, value: questionTypeOption.minQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: minQuestionsId, 'Minimal Number of Questions')
              }
              div(class:'input-field col s2') {
                input(id: maxQuestionsId, name: maxQuestionsName, value: questionTypeOption.maxQuestions, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: maxQuestionsId, 'Maximal Number of Questions')
              }
              div(class:'input-field col s2') {
                input(id: timeLimitId, name: timeLimitName, value: questionTypeOption.timeLimit, placeholder: 'No Restriction', type: 'number', min: 1, class: 'validate')
                label(class: 'active', for: timeLimitId, 'Time Limit')
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
        a(href: viewUrl, class: 'btn-large waves-effect waves-light red', name: 'cancel') {
          i (class:'material-icons right', 'cancel')
          yield 'Cancel'
        }
      }
    }
    // temp
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
          p (class: 'selectId', 'topicPriorities_topicId_' + index)
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
          p (class: 'selectId', 'questionTypeOptions_questionTypeId_' + index)
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
