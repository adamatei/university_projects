using NotificationService.Models;
using System.Collections.Generic;

namespace NotificationService.DataAccess
{
    public class FakeRepository
    {
        public List<Notification> Notifications { get; set; } = new List<Notification>() { new Notification { Id = 1, Content = "Ilya liked your post!", For = "Ada" }, new Notification { Id = 2, Content = "Ada mentioned you in a post", For = "Ilya" }, new Notification { Id = 3, Content = "Ada liked your post", For = "Ilya" } };
    }
}
