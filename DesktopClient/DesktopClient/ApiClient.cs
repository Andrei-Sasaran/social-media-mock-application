using DesktopClient.DTOs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DesktopClient
{
    internal class ApiClient
    {
        private readonly HttpClient _httpClient;

        public ApiClient()
        {
            _httpClient = new HttpClient();
            _httpClient.BaseAddress = new Uri("http://localhost:8082/api/");
        }

        public async Task<List<PostDTO>> GetPostsByKeywordAsync(string keyword)
        {
            var response = await _httpClient.GetAsync($"posts/search?keyword={keyword}");
            response.EnsureSuccessStatusCode();

            string json = await response.Content.ReadAsStringAsync();
            var posts = JsonSerializer.Deserialize<List<PostDTO>>(json,
                new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
            return posts ?? new List<PostDTO>();
        }

        public async Task<bool> ApprovePostAsync(long postId)
        {
            // PUT /api/posts/{postId}/approve
            var response = await _httpClient.PutAsync($"posts/{postId}/approve", null);
            return response.IsSuccessStatusCode;
        }

        public async Task<bool> RemovePostAsync(long postId)
        {
            // DELETE /api/posts/{postId}
            var response = await _httpClient.DeleteAsync($"posts/{postId}");
            return response.IsSuccessStatusCode;
        }

        public async Task<bool> SendEmailAsync(EmailDTO emailDto)
        {
            var options = new JsonSerializerOptions
            {
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase, // Convertim la camelCase
                WriteIndented = true // (opțional) pentru un JSON formatat frumos
            };

            var json = JsonSerializer.Serialize(emailDto,options);
           
            MessageBox.Show($"Serialized JSON:\n{json}"); // Debug: afișează JSON-ul serializat
            
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _httpClient.PostAsync("emails", content);
            return response.IsSuccessStatusCode;
        }

        public async Task<List<UserDTO>> GetAllUsersAsync()
        {
            var response = await _httpClient.GetAsync("user");
            response.EnsureSuccessStatusCode();

            string json = await response.Content.ReadAsStringAsync();
            var users = JsonSerializer.Deserialize<List<UserDTO>>(json,
                new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
            return users ?? new List<UserDTO>();
        }
    }
}
