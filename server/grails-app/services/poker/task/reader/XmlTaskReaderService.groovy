package poker.task.reader


class XmlTaskReaderService {

    List<Task> readTasks(InputStream xmlTaskStream) {
        def xmlTaskItem = new XmlSlurper().parse(xmlTaskStream).channel.item as List
        xmlTaskItem.collect { task ->
            readXmlTaskItem(task)
        }
    }

    private Task readXmlTaskItem(def task) {
        def id = task.key.@'id'.text() as Long
        def title = task.title.text()
        String htmlDescription = task.description.text() as String
        def project = task.project.text()
        return new Task(id: id, title: title, project: project, htmlDescription: htmlDescription)
    }
}

