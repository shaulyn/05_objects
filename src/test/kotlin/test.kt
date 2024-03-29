import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class test {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun testAddPost() {
        val post = Post(1, replyOwnerId=null, replyPostId=null)
        val addedPost = WallService.add(post)
        assertNotEquals(1, addedPost.id)
    }

    @Test
    fun testUpdateTrue() {
        val post = Post(0, replyOwnerId=null, replyPostId=null)
        WallService.add(post)
        val updatedPost = Post(post.id, replyOwnerId=null, replyPostId=null)
        val result = WallService.update(updatedPost)
        assertTrue(result)
    }

    @Test
    fun testUpdateFalse() {
        val post = Post(0, replyOwnerId=null, replyPostId=null)
        WallService.add(post)
        val updatedPost = Post(1, replyOwnerId=null, replyPostId=null)
        val result = WallService.update(updatedPost)
        assertFalse(result)
    }
}