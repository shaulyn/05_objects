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
    var replyPostId: Int?

)

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
    WallService.add(Post(0, replyOwnerId=null, replyPostId=null) )
    WallService.add(Post(1, replyOwnerId=null, replyPostId=null))
    WallService.print()
    WallService.add(Post(2, replyOwnerId=null, replyPostId=null))
    WallService.update(Post(1, ownerId = 2, text = "sdfdsfsdfsd", likes = Likes(10), replyOwnerId=1, replyPostId=1))
    WallService.update(Post(5, replyOwnerId=1, replyPostId=1))

    WallService.print()




}