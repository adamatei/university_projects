using MassTransit;
using MassTransitModels;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ProfileService.Interfaces;
using ProfileService.Models;
using ProfileService.Services;
using System.Collections.Generic;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ProfileService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProfileController : ControllerBase
    {
        private readonly IProfileManager service;
        IPublishEndpoint _publishEndpoint;
        public ProfileController(IPublishEndpoint publishEndpoint, IProfileManager service)
        {
            this.service = service;
            _publishEndpoint = publishEndpoint;
        }

        // GET: api/<ProfileController>
        [HttpGet]
        public List<Profile> Get()
        {
            return service.GetProfiles();
        }

        // GET api/<ProfileController>/5
        [HttpGet("id/{id}")]       
        public async Task<Profile> Get(int id)
        {
            return await service.GetProfileById(id);
        }

        // GET api/<ProfileController>/username1
        [HttpGet("{username}")]
        public Profile Get(string username)
        {
            return service.GetProfileByUsername(username);
        }

        // POST api/<ProfileController>
        [HttpPost]
        public async Task<Profile> Post([FromBody] Profile profile)
        {
            return await service.CreateProfile(profile);
        }

        // PUT api/<ProfileController>
        [HttpPut]
        public async Task<bool> Put([FromBody] Profile profile)
        {
            BlockUpdate blk = new BlockUpdate { Username = profile.Username, isBlocked = profile.isBlocked, Role = profile.Role};
            await _publishEndpoint.Publish<BlockUpdate>(blk);
            return await service.UpdateProfile(profile);
        }

        // DELETE api/<ProfileController>/5
        [HttpDelete("{id}")]
        public async Task<bool> Delete(int id)
        {
            Profile p = await service.GetProfileById(id);
            DeleteRequest del_req = new DeleteRequest { Username = p.Username };
            await _publishEndpoint.Publish<DeleteRequest>(del_req);
            System.Console.WriteLine("Profile deleted");
            return await service.DeleteProfile(id);                      
        }
    }
}
