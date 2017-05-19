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
  }
  p {
    button (href: '#', class: ' light-blue lighten-2 waves-effect waves-light btn') {
      yield 'Answers'
    }
  }
  a (class:'waves-effect waves-light btn-large red', href:'/personal/topics/' + topicId +'/questions/') {
    i (class:'material-icons left', 'cancel')
    yield 'Back'
  }
}
