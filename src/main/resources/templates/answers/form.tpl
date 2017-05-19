layout 'layout/main.tpl',
title:  'Edit Answers',
_csrf: _csrf,
content: contents {
  div(class: 'row') {
    h4(class: 'header', 'Related question: ' + question.text)
    h4(class: 'header', 'Question type: ' + questionTypes[question.questionType.id-1].name)
    form(id: 'answersForm', method:'post', class: 'col s12', modelAttribute: 'answerDtoList') {
      input (id: '_csrf', type:'hidden', name:_csrf.parameterName, value:_csrf.token)
      div(id: 'answersGrid') {
          answerDtoList.eachWithIndex { answer, index ->
            def answerRowId = 'answerRow_' + index.toString()
            div(class: 'answerRow row', id: answerRowId) {
                def answerTextId = 'answerText_' + index.toString()
                def radioButtonId = 'radioButton_' + index.toString()
                def checkBoxId = 'checkBox_' + index.toString()
                def serialNumberId = 'serialNumber_' + index.toString()

                //def answerTextName = 'question.answers['+index+'].text'
                //def rightName = 'question.answers['+index+'].right'
                //def serialNumberName = 'question.answers['+index+'].serialNumber'
                //def questionIdName = 'question.answers['+index+'].questionId'

                def answerTextName = 'answerDtoList['+index+'].txt'
                def rightName = 'answerDtoList['+index+'].right'
                def serialNumberName = 'answerDtoList['+index+'].serialNumber'
                def questionIdName = 'answerDtoList['+index+'].questionId'

                div(class: 'input-field col s4') {
                    input(id: answerTextId, name: answerTextName, type: 'text', value: answer.txt)
                    label(class: 'active', for: answerTextId, 'Original answer: ' + answer.txt)
                }
                if (question.questionType.id == 1) {
                    def radioButtonParams = [type: 'radio', name: rightName, class: 'with-gap', id: radioButtonId]
                    if (answer.right == true) {
                        radioButtonParams += [checked: true]
                    }
                    input (radioButtonParams)
                    label(for: radioButtonId){
                        yield ' ' // Радиокнопка не рисуется, если у неё нет ярлычка
                    }
                    input(name: rightName, type: 'hidden', value: answer.right)
                    input(name: serialNumberName, type: 'hidden', value: answer.serialNumber)
                }
                if (question.questionType.id == 2) {
                    def checkBoxParams = [type: 'checkbox', name: rightName, id: checkBoxId]
                    if (answer.right == true) {
                        checkBoxParams += [checked: true]
                    }
                    input(checkBoxParams)
                    label(for: checkBoxId){
                        yield ' ' // Чекбокс не рисуется, если у него нет ярлычка
                    }
                    input(name: rightName, type: 'hidden', value: answer.right)
                    input(name: serialNumberName, type: 'hidden', value: answer.serialNumber)
                }
                if (question.questionType.id == 3) {
                    div(class: 'input-field col s1') {
                    input(id: serialNumberId, name: serialNumberName, type: 'text', value: answer.serialNumber)
                    label(class: 'active', for: serialNumberId, 'Was ' + answer.serialNumber)
                    input(name: rightName, type: 'hidden', value: answer.right)
                    }
                }
                if (question.questionType.id == 4) {
                    input(name: rightName, type: 'hidden', value: answer.right)
                    input(name: serialNumberName, type: 'hidden', value: answer.serialNumber)
                }
                a (class: 'deleteAnswerRow btn-floating btn-small waves-effect waves-light right grey darken-1') {
                    i (class: 'material-icons', 'delete')
                    yield 'Delete'
                }
                div (hidden: true, id: 'questionIdHidden') {
                    input(name: questionIdName, type: 'hidden', value: question.id)
                }
            }
          }
      }
      div(class: 'row') {
        def buttonAttrs = [id: 'addAnswerRow', class: 'btn-floating btn-small waves-effect waves-light blue']
        a(buttonAttrs) {
          i(class: 'material-icons', 'add')
          yield 'Add'
        }
      }

       div(class: 'row') {
         button(type: 'submit', class: 'btn-large waves-effect waves-light blue', name: 'save') {
           i(class: 'material-icons right', 'send')
           yield 'Save'
         }
         a(href: '/personal/topics/' + topicId + '/questions/' + questionId + '/', class: 'btn-large waves-effect waves-light red', name: 'cancel') {
           i (class:'material-icons right', 'cancel')
           yield 'Cancel'
         }
       }
  }
        // I don't know yet about this, didn't test or anything.
        if (errors && errors.hasErrors()) {
          def errorMessages = errors.getAllErrors()*.getDefaultMessage()
          div (class: 'card-panel red-text text-darken-1') {
            errorMessages.each { message ->
              span(message)
            }
          }
        }

     div (hidden: true, id:'answersInfoHidden') {
        p (id: 'answerDtoListSize', answerDtoList.size())
        p (id: 'questionTypeId', question.questionType.id)
        p (id: 'questionId', question.id)
      }
  }
}
script(type:'text/javascript', src:'/js/update_answers.js') {}