package com.example.unitask.data

data class Project(
    val name: String,
    val description: String,
    val dueDate: String,
    val memberEmail: String? = null,
    val status: String = "Doing",
    val progress: Int = 0
)

object ProjectManager {
    private val projects = mutableListOf<Project>()

    init {
        // เพิ่มข้อมูลเริ่มต้น
        projects.add(Project("Mobile Project", "Describe", "7/1/2569", status = "Complete", progress = 65))
        projects.add(Project("Nahi Clinic", "Describe", "7/1/2569", status = "Complete", progress = 65))
    }

    fun addProject(project: Project) {
        projects.add(project)
    }

    fun getAllProjects(): List<Project> {
        return projects
    }
}