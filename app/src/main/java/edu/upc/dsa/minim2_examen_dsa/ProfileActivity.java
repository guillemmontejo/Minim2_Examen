package edu.upc.dsa.minim2_examen_dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    Usuario user;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView fullName = findViewById(R.id.fullName);
        TextView username = findViewById(R.id.username);
        TextView following = findViewById(R.id.followingTv);
        TextView followers = findViewById(R.id.followersTv);
        TextView repos = findViewById(R.id.reposTv);
        recyclerView =  findViewById(R.id.reposList);

        user = instanciaUsuario.getInstance().getUser();

        fullName.setText(user.getName());
        username.setText("Username: " + user.getLogin());
        following.setText("Following: " + String.valueOf(user.getFollowing()));
        followers.setText("Followers: " + String.valueOf(user.getFollowers()));
        repos.setText("Repositories: " + String.valueOf(user.getPublic_repos()));

        Picasso.with(getApplicationContext()).load(user.getAvatar_url()).into((ImageView) findViewById(R.id.image));
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Repo> input = user.getUserRepos();
        mAdapter = new RecyclerAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }
}