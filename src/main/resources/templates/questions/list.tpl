layout 'layout/main.tpl', title:  'Questions',
content: contents {
  h4 ('Questions')
  a (class:'right waves-effect waves-light btn-floating btn-large blue', href:'/personal/topics/' + topicId +'/questions/create/') {
    i (class:'material-icons left', 'add')
    yield 'Add'
  }
  table(class: 'striped') {
    thead {
      tr {
        th ('#')
        th ('Text')
        th ('Action')
      }
    }
    tbody {
      questions.each { question ->
        tr {
          td (question.id)
          td (question.text)
          td() {
            a (class:'waves-effect waves-light btn-floating blue', href:'/personal/topics/' + topicId +'/questions/' + question.id + '/') {
              i (class:'material-icons left', 'pageview')
              yield 'View'
            }
            a (class:'waves-effect waves-light btn-floating blue', href:'/personal/topics/' + topicId +'/questions/update/' + question.id + '/') {
              i (class:'material-icons left', 'edit')
              yield 'Edit'
            }
            a (class:'waves-effect waves-light btn-floating red', href:'/personal/topics/' + topicId +'/questions/delete/' + question.id + '/') {
              i (class:'material-icons left', 'delete')
              yield 'Delete'
            }
          }
        }
      }
    }
  }
}
