using Microsoft.EntityFrameworkCore;
using PostService.DataAccess;
using PostService.Interfaces;
using PostService.Models;
using Serilog;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostService.Managers
{
    public class PostManager : IPostManager
    {
        private readonly DatabaseContext db;

        public PostManager(DatabaseContext db)
        {
            this.db = db;
        }

        //creating a post
        public async Task<Post> CreatePost(Post post)
        {                
                List<Tag> tags = new List<Tag>();
                await db.Posts.AddAsync(post);
                await db.SaveChangesAsync();
                Log.Debug($"{DateTime.Now}: Post with id {post.Id} has been successfully posted");
                return post;           
        }

        //deleting a certain post
        public async Task<bool> DeletePost(int id)
        {
            if (db.Posts.ToList().Any(p => p.Id == id))
            {
                Post dbPost = db.Posts.Include(post => post.Tags).First(post => post.Id == id);
           
                foreach (var t in dbPost.Tags)
                {
                    db.Tags.Remove(t);
                }
                db.Posts.Remove(dbPost);
                await db.SaveChangesAsync();
                Log.Debug($"{DateTime.Now}: Post with id {id} has been successfully deleted");
                return true;
            }
            Log.Debug($"{DateTime.Now}: Post with id {id} could not deleted");
            return false;
        }

        public async Task<bool> DeletePostsByUsername(string username)
        {
            if (db.Posts.ToList().Any(p => p.CreatedBy == username))
            {
                List<Post> dbPosts = db.Posts.Where(post => post.CreatedBy == username).Include(post => post.Tags).ToList(); ;
                foreach (var post in dbPosts)
                {
                    foreach (var t in post.Tags)
                    {
                        db.Tags.Remove(t);
                    }
                    db.Posts.Remove(post);
                }
                await db.SaveChangesAsync();                
            }
            Log.Debug($"{DateTime.Now}: Posts of the user {username} has been successfully deleted");
            return true;
        }

        //retrieving a post by id
        public Post GetPostById(int id)
        {
            return db.Posts.Include(post => post.Tags).FirstOrDefault(post => post.Id == id);                 
        }

        //retrieving a list of posts by username
        public List<Post> GetPostsByUsername(string username)
        {
            return db.Posts.Include(post => post.Tags).Where(post => post.CreatedBy == username).ToList();
        }

        //updating a given post
        public async Task<Post> UpdatePost(Post post)
        {
            foreach (Post p in db.Posts.ToList())
            {
                if(p.Id == post.Id)
                {
                    p.Tags = post.Tags;
                    db.Update(p);
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Posts with id {post.Id} has been successfully updated");
                    return p;
                }
            }
            Log.Debug($"{DateTime.Now}: Posts with id {post.Id} could not be updated");
            return null;
        }      
    }
}
