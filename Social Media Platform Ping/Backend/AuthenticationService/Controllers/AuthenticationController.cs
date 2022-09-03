using MassTransit;
using MassTransitModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace AuthenticationService.Controllers
{
    
    [Route("api/[controller]")]
    [ApiController]
    public class AuthenticationController : ControllerBase
    {
        private readonly IJWTAuthenticationManager jwtAuthenticationManager;
        IPublishEndpoint _publishEndpoint;
        public AuthenticationController(IJWTAuthenticationManager jwtAuthenticationManager, IPublishEndpoint publishEindpoint)
        {
            this.jwtAuthenticationManager = jwtAuthenticationManager;
            _publishEndpoint = publishEindpoint;
        }

        
        // GET: api/<ValuesController>
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET api/<ValuesController>/5
        [HttpGet("{id}")]
        public string Get(string id)
        {
            EncodingAlgorithm alg = new EncodingAlgorithm();
            return alg.EncryptTripleDES(id);
        }

        [AllowAnonymous]
        [HttpPost("register")]
        public async Task<bool> Register([FromBody] UserCredentials userCred)
        {
            RegistrationUpdate reg = new RegistrationUpdate { Username = userCred.Username}; 
            await _publishEndpoint.Publish<RegistrationUpdate>(reg);
            var result = await jwtAuthenticationManager.Register(userCred.Username, userCred.Password);
            if(result != null) { return true; }
            return false;
        }

        [AllowAnonymous]
        [HttpPost ("authenticate")]
        public IActionResult Authenticate([FromBody] UserCredentials userCred)
        {
           var token =  jwtAuthenticationManager.Authenticate(userCred.Username, userCred.Password);
           if(token == null) return Unauthorized();
           return Ok(token);
        }
    }
}
