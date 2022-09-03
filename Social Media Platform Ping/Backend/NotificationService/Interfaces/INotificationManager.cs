using NotificationService.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace NotificationService.Interfaces
{
    public interface INotificationManager
    {
        List<Notification> GetNotificationsForUser(string username);
        Task<Notification> GetNotificationById(int id);
        Task<Notification> CreateNotification(Notification notification);
        Task<Notification> UpdateNotification(Notification notification);
        Task<bool> DeleteNotification(int id);

        Task<bool> DeleteNotificationsByUsername(string username);
    }
}
