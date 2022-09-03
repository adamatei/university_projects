using MassTransit;
using MassTransitModels;
using System;
using System.Threading.Tasks;

namespace AuthenticationService.Consumers
{
    public class DeleteRequestConsumer : IConsumer<DeleteRequest>
    {
        private readonly IJWTAuthenticationManager manager;

        public DeleteRequestConsumer(IJWTAuthenticationManager manager)
        {
            this.manager = manager;
        }

        public async Task Consume(ConsumeContext<DeleteRequest> context)
        {
            var msg = context.Message;

            try
            {
                await manager.DeleteAccount(msg.Username);

                await Console.Out.WriteLineAsync(msg.Username + " is deleted ");
            }
            catch (Exception e)
            {
                await Console.Out.WriteLineAsync(e.Message);
            }
        }
    }
}
