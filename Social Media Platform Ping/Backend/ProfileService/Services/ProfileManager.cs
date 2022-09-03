using ProfileService.DataAccess;
using ProfileService.Interfaces;
using ProfileService.Models;
using Serilog;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ProfileService.Services
{
    public class ProfileManager : IProfileManager
    {
        private readonly DatabaseContext db;
        private readonly EncrytionLogic encryption;

        //CONSTRUCTOR
        public ProfileManager(DatabaseContext db, EncrytionLogic encryption)
        {
            this.db = db;
            this.encryption = encryption;
        }

        //creating profile
        public async Task<Profile> CreateProfile(Profile profile)
        {           
           if(!db.Profiles.Any(p => p.Username == profile.Username))
           {
                profile.Job = encryption.EncryptTripleDES(profile.Job);             
                profile.Education = encryption.EncryptTripleDES(profile.Education);
                profile.LivingCity = encryption.EncryptTripleDES(profile.LivingCity);
                profile.LivingCountry = encryption.EncryptTripleDES(profile.LivingCountry);
                profile.OriginCityy = encryption.EncryptTripleDES(profile.OriginCityy);
                profile.OriginCountry = encryption.EncryptTripleDES(profile.OriginCountry);
                profile.Intro = encryption.EncryptTripleDES(profile.Intro);
                await db.Profiles.AddAsync(profile);
                await db.SaveChangesAsync();
                Log.Debug($"{DateTime.Now}: Profile with username {profile.Username} has been successfully created");
                return profile;
           }
            Log.Debug($"{DateTime.Now}: Profile with username {profile.Username} could not be created");
            return null;
        }

        //deleting a profile
        public async Task<bool> DeleteProfile(int id)
        {
            foreach (var profile in db.Profiles.ToList())
            {
                if(profile.Id == id)
                {
                    db.Profiles.Remove(profile);
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Profile with id {id} has been successfully deleted");
                    return true;
                }
            }
            Log.Debug($"{DateTime.Now}: Profile with id {id} could not be deleted");
            return false;
        }

        //retrieving a profile by id
        public async Task<Profile> GetProfileById(int id)
        {
            Profile profile =  await db.Profiles.FindAsync(id);
            if (profile == null)
            {
                Log.Debug($"{DateTime.Now}: Profile with id {id} could not be found");
                return null;
            }
            Log.Debug($"{DateTime.Now}: Profile with id {id} is retrieved");
            return encryption.DecryptProfile(profile);
        }

        //retrieving a profile by username
        public Profile GetProfileByUsername(string username)
        {
            foreach (var profile in db.Profiles.ToList())
             {
                 if (profile.Username == username)
                 {
                    Log.Debug($"{DateTime.Now}: Profile with username {username} is retrieved");
                    return encryption.DecryptProfile(profile);
                 }
             }
            Log.Debug($"{DateTime.Now}: Profile with username {username} could not be found");
            return null;           
        }

        //retrieving all profiles
        public List<Profile> GetProfiles()
        {
            List<Profile> tempProfiles = new List<Profile>();
            foreach (var profile in db.Profiles.ToList())
            {
                tempProfiles.Add(encryption.DecryptProfile(profile));
            }
            return tempProfiles;
        }

        //updating a given profile
        public async Task<bool> UpdateProfile(Profile new_profile)
        {
            foreach (var profile in db.Profiles.ToList())
            {
                if (profile.Id == new_profile.Id)
                {                   
                    profile.Job = encryption.EncryptTripleDES(new_profile.Job);
                    profile.Role = new_profile.Role;
                    profile.Education = encryption.EncryptTripleDES(new_profile.Education);
                    profile.Birthday = new_profile.Birthday;
                    profile.LivingCity = encryption.EncryptTripleDES(new_profile.LivingCity);
                    profile.LivingCountry = encryption.EncryptTripleDES(new_profile.LivingCountry);
                    profile.OriginCityy = encryption.EncryptTripleDES(new_profile.OriginCityy);
                    profile.OriginCountry = encryption.EncryptTripleDES(new_profile.OriginCountry);                
                    profile.Intro = encryption.EncryptTripleDES(new_profile.Intro);
                    profile.isPublic = new_profile.isPublic;
                    profile.isBlocked = new_profile.isBlocked;                 
                    db.Update(profile);
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Profile with username {new_profile.Username} was successfully updated");
                    return true;            
                }
            }
            Log.Debug($"{DateTime.Now}: Profile with username {new_profile.Username} could not be updated updated, as it was not found");
            return false;
        }
    }
}
