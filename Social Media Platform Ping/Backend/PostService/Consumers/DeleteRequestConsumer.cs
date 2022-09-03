using MassTransit;
using MassTransitModels;
using PostService.Interfaces;
using System;
using System.Threading.Tasks;

namespace PostService.Consumers
{
    public class DeleteRequestConsumer : IConsumer<DeleteRequest>
    {
        private readonly IPostManager manager;

        public DeleteRequestConsumer(IPostManager manager)
        {
            this.manager = manager;
        }

        public async Task Consume(ConsumeContext<DeleteRequest> context)
        {

            var msg = context.Message;

            try
            {
                await manager.DeletePostsByUsername(msg.Username);

                await Console.Out.WriteLineAsync(msg.Username + " is deleted ");
            }
            catch (Exception e)
            {
                await Console.Out.WriteLineAsync(e.Message);
            }
        }
    }
}
