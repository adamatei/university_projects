#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:5.0 AS base
WORKDIR /app
EXPOSE 80
#EXPOSE 1433

FROM mcr.microsoft.com/dotnet/sdk:5.0 AS build
WORKDIR /src
COPY ["Backend/AuthenticationService/AuthenticationService.csproj", "Backend/AuthenticationService/"]
RUN dotnet restore "Backend/AuthenticationService/AuthenticationService.csproj"
COPY . .
#WORKDIR "/src/AuthenticationService"
RUN dotnet build "Backend/AuthenticationService/AuthenticationService.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "Backend/AuthenticationService/AuthenticationService.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "AuthenticationService.dll"]
