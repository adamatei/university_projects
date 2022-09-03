using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Caching.Distributed;
using NotificationService.Interfaces;
using NotificationService.Managers;
using NotificationService.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace NotificationService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NotificationController : ControllerBase
    {
        private readonly INotificationManager service;
        private readonly IDistributedCache _cache;
        public NotificationController(INotificationManager service, IDistributedCache cache)
        {
            this.service = service;
            this._cache = cache;
        }

        // GET: api/<NotificationController>
        [HttpGet("{username}")]
        public async Task<IEnumerable<Notification>> GetNotificationsForUserAsync(string username)
        {
            var cacheKey = "GET_ALL_NOTIFICATIONS_BY_USERNAME_" + username;
            List<Notification> notifications = new List<Notification>();
            var data = await _cache.GetRecordAsync<List<Notification>>(cacheKey);
            if (data is null)
            {
                data = service.GetNotificationsForUser(username);
                await _cache.SetRecordAsync(cacheKey, data);
            }
            return data;
           //return service.GetNotificationsForUser(username);
        }

        // GET api/<NotificationController>/5
        [HttpGet("id/{id}")]
        public async Task<Notification> GetNotificationById(int id)
        {
            return await service.GetNotificationById(id);
        }

        // POST api/<NotificationController>
        [HttpPost]
        public async Task<Notification> Post([FromBody] Notification notification)
        {
            return await service.CreateNotification(notification);
        }

        // PUT api/<NotificationController>
        [HttpPut]
        public async Task<Notification> Put([FromBody] Notification notification)
        {
            return await service.UpdateNotification(notification);
        }

        // DELETE api/<NotificationController>/5
        [HttpDelete("{id}")]
        public async Task<bool> Delete(int id)
        {
            return await service.DeleteNotification(id);
        }
    }
}
