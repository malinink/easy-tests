layout 'layout/main.tpl', title:  'Question',
content: contents {
  h4 ('Question')
  form (class:'col s12') {
    div (class:'row') {
      div (class:'input-field col s12') {
        textarea (id:'question_text', class:'materialize-textarea black-text', readonly: 'readonly') {
            yield question.text
        }
        label (for:'question_text', class: 'grey-text', 'Question')
      }
    }
    div (class:'row') {
      div (class:'col s12') {
        h6 ('Question type', class: 'grey-text')
        def id = 0
        questionTypes.each { questionType ->
          id++
          if (question.questionType.id == id)
          {
            p {
              input (id: id, type: 'radio', name: 'questionType', disabled: 'disabled', checked: 'checked')
              label (for: id, class: 'black-text', questionType.name)
            }
          }
          else
          {
            p {
              input (id: id, type: 'radio', name: 'questionType', disabled: 'disabled')
              label (for: id, questionType.name)
            }
          }
        }
      }
    }
       div(class: 'row') {
       def answersList = question.answers
        if ((question.questionType.id == 1) || (question.questionType.id == 2)) {
          answersList.each { answer ->
            div (class:'input-field col s12') {
                if (answer.right == true) {
                  input (id:'answer_' + answer.id, type:'checkbox', checked: 'checked', disabled: 'disabled')
                }
                else {
                  input (id:'answer_' + answer.id, type:'checkbox', disabled: 'disabled')
                }
                  label (for:'answer_' + answer.id, class: 'grey-text', answer.txt)
             }
          }
        }
        if (question.questionType.id == 3) {
          answersList.each { answer ->
            div (class:'input-field col s12') {
                def serialText = answer.serialNumber.toString() + ': ' + answer.txt
                yield serialText
             }
          }
        }
        if (question.questionType.id == 4) {
          answersList.each { answer ->
            div (class:'input-field col s12') {
              textarea (id:'answer_text', class:'materialize-textarea black-text', readonly: 'readonly') {
              yield answer.txt
              }
              label (for:'answer_text', class: 'grey-text', 'Answer')
            }
          }
        }
      }
      p {
        a (class: 'light-blue lighten-2 waves-effect waves-light btn', href: '/personal/topics/' + topicId + '/questions/' + question.id + '/update_answers/') {
          i (class:'material-icons left', 'edit')
          yield 'Edit Answers'
        }
      }
      a (class:'waves-effect waves-light btn red', href:'/personal/topics/' + topicId +'/questions/') {
        i (class:'material-icons left', 'cancel')
        yield 'Back'
      }
   }
}
