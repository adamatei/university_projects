using FollowService.DataAccess;
using FollowService.Interfaces;
using FollowService.Models;
using Serilog;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FollowService.Managers
{
    public class FollowManager : IFollowManager
    {
        private readonly DatabaseContext db;

        public FollowManager(DatabaseContext db)
        {
            this.db = db;
        }

        //creating a follow request
        public async Task<Follow> CreateFollow(Follow follow)
        {
            foreach (var f in db.Follows.ToList())
            {
                if (f.Followed == follow.Followed && f.Follower == follow.Follower)
                {
                    Log.Debug($"{DateTime.Now}: Follow Request could not be created: ALREADY EXISTING IN DB");
                    return null;
                }
            }
            await db.Follows.AddAsync(follow);
            await db.SaveChangesAsync();
            Log.Debug($"{DateTime.Now}: Follow Request has been successfully created");
            return follow;
        }

        //deleting a follow connection
        public async Task<bool> DeleteFollow(int id)
        {
            foreach (var f in db.Follows.ToList())
            {
                if(f.Id == id)
                {
                    db.Follows.Remove(f);
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Follow Request with id {id} has been successfully deleted");
                    return true;
                }
            }
            Log.Debug($"{DateTime.Now}: Follow Request with id {id} could not be deleted: NOT FOUND");
            return false;
        }

        public async Task<bool> DeleteFollowsByUsername(string username)
        {
            foreach (var f in db.Follows.ToList())
            {
                if (f.Follower == username || f.Followed == username)
                {
                    db.Follows.Remove(f);
                    await db.SaveChangesAsync();                  
                }
            }
            Log.Debug($"{DateTime.Now}: Follow Requests including username {username} have been successfully deleted");
            return true;
        }

        //retrieving a follow relation by id
        public async Task<Follow> GetFollowById(int id)
        {
            return await db.Follows.FindAsync(id);
        }

        //retrieving a list of followed profiles by user
        public List<string> GetFollowedOfUser(string username)
        {
            List<string> followed = new List<string>();
            foreach (var f in db.Follows.ToList())
            {
                if (f.Follower == username && f.isAccepted == true)
                {
                    followed.Add(f.Followed);                   
                }
            }
            return followed;
        }

        //retrieving a list of profiles that follow the user
        public List<string> GetFollowersOfUser(string username)
        {
            List<string> followers = new List<string>();
            foreach (var f in db.Follows.ToList())
            {
                if (f.Followed == username && f.isAccepted == true)
                {
                    followers.Add(f.Follower);
                }
            }
            return followers;
        }

        //retrieve all follow request for username
        public List<Follow> GetFollowRequest(string username)
        {
            List<Follow> requests = new List<Follow>();
            foreach (var f in db.Follows.ToList())
            {
                if (f.Followed == username && f.isPending == true)
                {
                    requests.Add(f);
                }
            }
            return requests;
        }

        //updating a certain follow relation
        public async Task<Follow> UpdateFollow(Follow follow)
        {
            foreach (var f in db.Follows.ToList())
            {
                if (f.Id == follow.Id)
                {
                    f.Followed = follow.Followed;
                    f.Follower = follow.Follower;
                    f.isAccepted = follow.isAccepted;
                    f.isPending = follow.isPending;
                    db.Update(f);
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Follow Request with id {follow.Id} has been successfully updated");
                    return f;
                }
            }
            Log.Debug($"{DateTime.Now}: Follow Request with id {follow.Id} could not be updated: NOT FOUND");
            return null;
        }
    }
}
