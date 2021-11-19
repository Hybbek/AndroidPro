package at.fhooe.me.semesterproject

/*data class CovidRestrictions (
        val areaAccessRestrictions: AreaAccessRestrictions,
        val areaRestriction: List<AreaRestriction>
)

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
)*/

data class CovidRestrictions(
    val `data`: Data,
    val meta: Meta
)

data class Data(
    val area: Area,
    val areaAccessRestriction: AreaAccessRestriction,
    val areaPolicy: AreaPolicy,
    val areaRestrictions: List<AreaRestriction>,
    val areaVaccinated: List<AreaVaccinated>,
    val dataSources: DataSources,
    val diseaseCases: DiseaseCases,
    val diseaseInfection: DiseaseInfection,
    val diseaseRiskLevel: String,
    val relatedArea: List<RelatedArea>,
    val summary: String,
    val type: String
)

data class Meta(
    val links: Links
)

data class Area(
    val areaType: String,
    val iataCode: String,
    val name: String
)

data class AreaAccessRestriction(
    val declarationDocuments: DeclarationDocuments,
    val diseaseTesting: DiseaseTesting,
    val diseaseVaccination: DiseaseVaccination,
    val entry: Entry,
    val exit: Exit,
    val mask: Mask,
    val otherRestriction: OtherRestriction,
    val quarantineModality: QuarantineModality,
    val tracingApplication: TracingApplication,
    val transportation: Transportation
)

data class AreaPolicy(
    val date: String,
    val endDate: String,
    val referenceLink: String,
    val startDate: String,
    val status: String,
    val text: String
)

data class AreaRestriction(
    val date: String,
    val restrictionType: String,
    val text: String
)

data class AreaVaccinated(
    val date: String,
    val percentage: Double,
    val vaccinationDoseStatus: String
)

data class DataSources(
    val covidDashboardLink: String,
    val governmentSiteLink: String
)

data class DiseaseCases(
    val confirmed: Int,
    val date: String,
    val deaths: Int
)

data class DiseaseInfection(
    val date: String,
    val level: String,
    val rate: Double
)

data class RelatedArea(
    val methods: List<String>,
    val rel: String
)

data class DeclarationDocuments(
    val date: String,
    val documentRequired: String,
    val text: String,
    val travelDocumentationLink: String
)

data class DiseaseTesting(
    val date: String,
    val isRequired: String,
    val minimumAge: Int,
    val requirement: String,
    val rules: String,
    val testType: String,
    val text: String,
    val validityPeriod: ValidityPeriod,
    val `when`: String
)

data class DiseaseVaccination(
    val acceptedCertificates: List<String>,
    val date: String,
    val exemptions: String,
    val isRequired: String,
    val policy: String,
    val qualifiedVaccines: List<String>,
    val referenceLink: String,
    val text: String
)

data class Entry(
    val ban: String,
    val bannedArea: List<BannedArea>,
    val borderBan: List<BorderBan>,
    val date: String,
    val exemptions: String,
    val rules: String,
    val text: String,
    val throughDate: String
)

data class Exit(
    val date: String,
    val isBanned: String,
    val specialRequirements: String
)

data class Mask(
    val date: String,
    val isRequired: String,
    val text: String
)

data class OtherRestriction(
    val date: String
)

data class QuarantineModality(
    val date: String,
    val duration: Int,
    val eligiblePerson: String,
    val mandateList: String,
    val quarantineOnArrivalAreas: List<QuarantineOnArrivalArea>,
    val quarantineType: String,
    val rules: String,
    val text: String
)

data class TracingApplication(
    val androidUrl: List<String>,
    val date: String,
    val iosUrl: List<String>,
    val isRequired: String,
    val text: String
)

data class Transportation(
    val date: String,
    val isBanned: String,
    val text: String,
    val transportationType: String
)

data class ValidityPeriod(
    val delay: String,
    val referenceDateType: String
)

data class BannedArea(
    val areaType: String,
    val iataCode: String
)

data class BorderBan(
    val borderType: String,
    val status: String
)

data class QuarantineOnArrivalArea(
    val areaType: String,
    val iataCode: String
)

data class Links(
    val self: String
)
