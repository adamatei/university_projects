using MassTransit;
using Microsoft.AspNetCore.Mvc;
using PostService.Interfaces;
using PostService.Managers;
using PostService.Models;
using MassTransitModels;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Linq;
using PostService.DataAccess;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace PostService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PostController : ControllerBase
    {
        private readonly IPostManager service;            
        IPublishEndpoint _publishEndpoint;

        public PostController(IPublishEndpoint publishEndpoint, IPostManager service)
        {
            _publishEndpoint = publishEndpoint;
            this.service = service;
        }

        // GET: api/<PostController>/username
        [HttpGet("{username}")]
        public List<Post> GetPostsByUsername(string username)
        {
            return service.GetPostsByUsername(username);
        }

        // GET api/<PostController>/5
        [HttpGet("id/{id}")]
        public Post GetPostById(int id)
        {
            return service.GetPostById(id);
        }
              

        // POST api/<PostController>
        [HttpPost]
        public async Task<IActionResult> Post([FromBody] Post post)
        {
            await service.CreatePost(post);
           
            //publisher actions
            if (post.Tags.Any(t => t.Type == "mention"))
            {
                  foreach (var m in post.Tags)
                  {
                    if(m.Type == "mention") 
                    { 
                      MentionNotification mention = new MentionNotification { Mentioned = m.Content, Mentioner = post.CreatedBy, MentionedOn = post.CreatedOn, PostId = post.Id, PostText = post.Text };
                      await _publishEndpoint.Publish<MentionNotification>(mention);
                    }
                  }
            }
            return Ok();
        }

        // PUT api/<PostController>
        [HttpPut]
        public async Task<IActionResult> Put([FromBody] Post post)
        {
            //publisher actions
            int total = 0;
            foreach (var tag in post.Tags)
            {
                if(tag.Type == "like")
                {
                    total++;
                }
            }
              Like like = new Like { Total = total, LikedOn = System.DateTime.Now, LikedUsername = post.CreatedBy, PostId = post.Id, PostText = post.Text};
              await _publishEndpoint.Publish<Like>(like);

            await service.UpdatePost(post);
                      
            return Ok();
        }


        // DELETE api/<PostController>/5
        [HttpDelete("{id}")]
        public async Task<bool> Delete(int id)
        {
            return await service.DeletePost(id);
        }
    }
}
