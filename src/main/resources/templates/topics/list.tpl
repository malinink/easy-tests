layout 'layout/main.tpl', title:  'Your topics list',
content: contents {
  h4 ('Your topics list')
  a (class:'right waves-effect waves-light btn-floating btn-large blue') {
    i (class:'material-icons left', 'add', onclick:'document.location.href="/personal/topics/create"')
    yield 'Add'
  }
  table(class: 'striped') {
    thead {
      tr {
        th('#')
        th('Name')
        th('Action')
      }
    }
    tbody {
      topics.eachWithIndex { topic, number ->
        tr {
          td(number + 1)
          td(topic.getName())
          td() {
            a (class:'waves-effect waves-light btn-floating blue') {
              i (class:'material-icons left', 'pageview', onclick:'document.location.href="/personal/topics/'+topic.id + '"')
              yield 'View'
            }
            a (class:'waves-effect waves-light btn-floating blue') {
              i (class:'material-icons left', 'edit', onclick:'document.location.href="/personal/topics/update/'+topic.id + '"')
              yield 'Edit'
            }
            a (class:'waves-effect waves-light btn-floating red') {
              i (class:'material-icons left', 'delete', onclick:'document.location.href="/personal/topics/delete/'+topic.id + '"')
              yield 'Delete'
            }
          }
        }
      }
    }
  }
}