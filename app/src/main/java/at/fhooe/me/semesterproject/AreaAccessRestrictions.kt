package at.fhooe.me.semesterproject

data class AreaAccessRestrictions(
    val transport: Transport,
    val documentsRequired: String,
    val entry: Entry
)

data class Transport(
    val date: String,
    val text: String,
    val transportationType: String,
    val isBanned: String
)


data class Entry(
    val text: String,
    val ban: String,
    val rulesLink: String,
    //val bannedArea: Array<String>,
)
