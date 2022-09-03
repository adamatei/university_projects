using FollowService.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace FollowService.Interfaces
{
    public interface IFollowManager
    {
        List<string> GetFollowersOfUser(string username);
        Task<Follow> GetFollowById(int id);
        List<string> GetFollowedOfUser(string username);
        Task<Follow> CreateFollow(Follow follow);
        Task<bool> DeleteFollow(int id);
        Task<Follow> UpdateFollow(Follow follow);

        Task<bool> DeleteFollowsByUsername(string username);
        List<Follow> GetFollowRequest(string username);
    }
}
