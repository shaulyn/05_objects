data class Post(
    var id: Int,
    var ownerId: Int = 1,
    var fromId: Int=1,
    var text: String ="",
    var copyright: String ="",
    var canPin:Boolean = false,
    var canDelete:Boolean = false,
    var canEdit:Boolean = false,
    var likes: Likes=Likes(1),
    var replyOwnerId: Int?,
    var replyPostId: Int?,
    val attachments : Array<Attachment> = emptyArray()

)

interface Attachment {
    val type: String
}


data class Audio(
    val id : Int,
    val ownerId : Int,
    val artist : String,
    val title : String,
    val duration : Int,
    val url : String,
    val lyricsId : Int,
    val albumId : Int,
    val genreId : Int,
    val date : Int,
    val noSearch : Int,
    val isHq : Int,
)

class AudioAttachment(param : Audio) : Attachment {
    override val type: String = "audio"
    val audio = param

    override fun toString() : String{
        return "\n  $audio"
    }
}
data class Note(
    val id : Int,
    val ownerId : Int,
    val title : String,
    val text : String,
    val date : Int,
    val comments : Int,
    val readComments : Int,
    val viewUrl : String
)

class NoteAttachment(param : Note) : Attachment {
    override val type: String = "note"
    val note = param

    override fun toString() : String{
        return "\n  $note"
    }
}

data class Link(
    val url : String,
    val title : String,
    val caption : String,
    val description : String,
    val photo : String,
    val comments : Int,
    val readComments : Int,
    val viewUrl : String
)
class LinkAttachment(param : Link) : Attachment {
    override val type: String = "link"
    val link = param

    override fun toString() : String{
        return "\n  $link"
    }
}

data class Wall(
    val productId : Int,
    val stickerId : Int
)
class WallAttachment(param : Wall) : Attachment {
    override val type: String = "wall"
    val wall = param

    override fun toString() : String{
        return "\n  $wall"
    }
}

data class Video(
    val id : Int,
    val owneId : Int,
    val title : String,
    val description : String,
    val duration : Int,
    val date : Int,
    val adding_date : Int,
    val views : Int
)

class VideoAttachment(param : Video) : Attachment {
    override val type: String = "video"
    val video = param

    override fun toString() : String{
        return "\n  $video"
    }

}


data class Likes(
    var count: Int
)

object WallService{
    private var posts = arrayOf<Post>()
    private var lastId = 0

    fun add(post: Post):Post{
        val newPost= post.copy(id= lastId)
        lastId+=1
        posts += newPost
        return newPost

    }

    fun update(post: Post): Boolean {
        for(index in posts.indices){
            if(posts[index].id==post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        println("Не сработало, так как нет такого ID")
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun print() {
        for (post in posts) {
            println(post)
        }
        println()
    }

}



fun main(){
    var audio = Audio(
        10,
        12454353,
        "artist",
        "title",
        3234,
        "url",
        123,
        56,
        3,
        34234,
        4,
        9
    )
    var note = Note(
        10,
        12454353,
        "artist",
        "title",
        3234,
        100,
        123,
        "dfgdfgdf"

    )


    var attachmentAudio = AudioAttachment(audio)
    var attachmentNote = NoteAttachment(note)




    WallService.add(Post(0, replyOwnerId=null, replyPostId=null, attachments = arrayOf(attachmentAudio,attachmentNote)) )
    WallService.add(Post(1, replyOwnerId=null, replyPostId=null, attachments = arrayOf(attachmentAudio,attachmentNote)))
    WallService.print()
    WallService.add(Post(2, replyOwnerId=null, replyPostId=null, attachments = arrayOf(attachmentAudio,attachmentNote)))
    WallService.update(Post(1, ownerId = 2, text = "sdfdsfsdfsd", likes = Likes(10), replyOwnerId=1, replyPostId=1, attachments = arrayOf(attachmentAudio,attachmentNote)))
    WallService.update(Post(5, replyOwnerId=1, replyPostId=1, attachments = arrayOf(attachmentAudio,attachmentNote)))

    WallService.print()




}