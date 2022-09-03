using FollowService.Interfaces;
using FollowService.Managers;
using FollowService.Models;
using MassTransit;
using MassTransitModels;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace FollowService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FollowController : ControllerBase
    {
        private readonly IFollowManager service;
        IPublishEndpoint _publishEndpoint;

        public FollowController(IPublishEndpoint publishEndpoint, IFollowManager service)
        {
            _publishEndpoint = publishEndpoint;
            this.service = service;
        }

        // GET: api/<FollowController>/followers/Sasha12
        [HttpGet("followers/{username}")]
        public IEnumerable<string> GetFollowers(string username)
        {
            return service.GetFollowersOfUser(username);
        }

        // GET: api/<FollowController>/followed/Sasha12
        [HttpGet("followed/{username}")]
        public IEnumerable<string> GetFollowed(string username)
        {
            return service.GetFollowedOfUser(username);
        }

        // GET api/<FollowController>/5
        [HttpGet("{id}")]
        public async Task<Follow> Get(int id)
        {
            return await service.GetFollowById(id);
        }

        // GET api/<FollowController>/5
        [HttpGet("requests/{username}")]
        public List<Follow> GetRequests(string username)
        {
            return service.GetFollowRequest(username);
        }

        // POST api/<FollowController>
        [HttpPost]
        public async Task<Follow> Post([FromBody] Follow follow)
        {
            FollowRequest follow_request = new FollowRequest { Id = follow.Id, Followed = follow.Followed, Follower = follow.Follower, isAccepted = follow.isAccepted, isPending = follow.isPending };
            await _publishEndpoint.Publish<FollowRequest>(follow_request);
            return await service.CreateFollow(follow);
        }

        // PUT api/<FollowController>
        [HttpPut]
        public async Task<Follow> Put(int id, [FromBody] Follow follow)
        {
            return await service.UpdateFollow(follow);
        }

        // DELETE api/<FollowController>/5
        [HttpDelete("{id}")]
        public async Task<bool> Delete(int id)
        {
            return await service.DeleteFollow(id);
        }
    }
}
