using PostService.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace PostService.Interfaces
{
    public interface IPostManager
    {
        List<Post> GetPostsByUsername(string username);
        Post GetPostById(int id);
        Task<bool> DeletePost(int id);
        Task<Post> CreatePost(Post post);
        Task<Post> UpdatePost(Post post);
        Task<bool> DeletePostsByUsername(string username);
    }
}
