layout 'layout/main.tpl', title:  'Your subjects list',
content: contents {
h4 ('Your subjects list')
a (class:'right waves-effect waves-light btn-floating btn-large blue') {
i (class:'material-icons left', 'add', onclick:'document.location.href="/personal/subjects/create"')
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
subjects.eachWithIndex { subject, number ->
tr {
td(number + 1)
td(subject.getName())
td() {
a (class:'waves-effect waves-light btn-floating blue') {
i (class:'material-icons left', 'pageview', onclick:'document.location.href="/personal/subjects/'+subject.id + '"')
yield 'View'
}
a (class:'waves-effect waves-light btn-floating blue') {
i (class:'material-icons left', 'edit', onclick:'document.location.href="/personal/subjects/update/'+subject.id + '"')
yield 'Edit'
}
a (class:'waves-effect waves-light btn-floating red') {
i (class:'material-icons left', 'delete', onclick:'document.location.href="/personal/subjects/delete/'+subject.id + '"')
yield 'Delete'
}
}
}
}
}
}
}
