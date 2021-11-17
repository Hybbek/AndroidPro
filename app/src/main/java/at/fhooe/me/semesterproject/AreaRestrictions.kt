package at.fhooe.me.semesterproject

data class AreaRestrictions(
    val array: Array<RestrictionObject>
)

data class RestrictionObject(
    val date: String,
    val text: String,
    val restrictionType: String
)

