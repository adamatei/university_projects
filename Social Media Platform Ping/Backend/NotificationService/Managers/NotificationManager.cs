using NotificationService.DataAccess;
using NotificationService.Interfaces;
using NotificationService.Models;
using Serilog;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService.Managers
{
    public class NotificationManager : INotificationManager
    {
        private readonly DatabaseContext db;

        public NotificationManager(DatabaseContext db)
        {
            this.db = db;
        }

        //creating a notification
        public async Task<Notification> CreateNotification(Notification notification)
        {
            foreach (var not in db.Notifications.ToList())
            {
                if(not.Id == notification.Id)
                {
                    return null;
                }
            }
            await db.Notifications.AddAsync(notification);
            await db.SaveChangesAsync();
            Log.Debug($"{DateTime.Now}: Notification with content {notification.Content} has been successfully created");
            return notification;
        }

        //deleting a notification
        public async Task<bool> DeleteNotification(int id)
        {
            foreach (var not in db.Notifications.ToList())
            {
                if (not.Id == id)
                {
                    db.Notifications.Remove(not);
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Notification with id {id} has been successfully deleted");
                    return true;
                }
            }
            Log.Debug($"{DateTime.Now}: Notification with id {id} could not be deleted, as it was not found");
            return false;
        }

        public async Task<bool> DeleteNotificationsByUsername(string username)
        {
            foreach (var not in db.Notifications.ToList())
            {
                if (not.For == username)
                {
                    db.Notifications.Remove(not);
                    await db.SaveChangesAsync();                    
                }
            }
            Log.Debug($"{DateTime.Now}: Notifications for user {username} have been successfully deleted");
            return true;
        }

        //retrieving a notification by id
        public async Task<Notification> GetNotificationById(int id)
        {
            return await db.Notifications.FindAsync(id);
        }

        //retrieving notifications for a specific user
        public List<Notification> GetNotificationsForUser(string username)
        {
            List<Notification> tempList = new List<Notification>();
            foreach (var not in db.Notifications.ToList())
            {
                if (not.For == username)
                {
                    tempList.Add(not);
                }
            }
            return tempList;
        }

        //updating a certain notification
        public async Task<Notification> UpdateNotification(Notification notification)
        {
            foreach (var not in db.Notifications.ToList())
            {
                if (not.Id == notification.Id)
                {
                    not.Content = notification.Content;
                    not.For = notification.For;
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Notification with id {notification.Id} has been successfully updated");
                    return not;
                }
            }
            Log.Debug($"{DateTime.Now}: Notification with id {notification.Id} could not be updated: NOT FOUND");
            return null;
        }
    }
}
