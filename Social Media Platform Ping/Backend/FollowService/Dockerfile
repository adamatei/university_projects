#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:5.0 AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:5.0 AS build
WORKDIR /src
COPY ["Backend/FollowService/FollowService.csproj", "Backend/FollowService/"]
RUN dotnet restore "Backend/FollowService/FollowService.csproj"
COPY . .
#WORKDIR "/src/FollowService"
RUN dotnet build "Backend/FollowService/FollowService.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "Backend/FollowService/FollowService.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "FollowService.dll"]