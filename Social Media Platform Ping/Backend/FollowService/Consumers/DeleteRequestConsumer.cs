using FollowService.Interfaces;
using MassTransit;
using MassTransitModels;
using System;
using System.Threading.Tasks;

namespace FollowService.Consumers
{
    public class DeleteRequestConsumer : IConsumer<DeleteRequest>
    {
        private readonly IFollowManager manager;

        public DeleteRequestConsumer(IFollowManager manager)
        {
            this.manager = manager;
        }
        public async Task Consume(ConsumeContext<DeleteRequest> context)
        {
            var msg = context.Message;

            try
            {
                await manager.DeleteFollowsByUsername(msg.Username);

                await Console.Out.WriteLineAsync(msg.Username + " is deleted ");
            }
            catch (Exception e)
            {
                await Console.Out.WriteLineAsync(e.Message);
            }
        }
    }
}
