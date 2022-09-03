using ProfileService.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace ProfileService.Interfaces
{
    public interface IProfileManager
    {
        List<Profile> GetProfiles();
        Task<Profile> GetProfileById(int id);
        Profile GetProfileByUsername(string username);
        Task<bool> UpdateProfile(Profile new_profile);
        Task<bool> DeleteProfile(int id);
        Task<Profile> CreateProfile(Profile profile);
    }
}
