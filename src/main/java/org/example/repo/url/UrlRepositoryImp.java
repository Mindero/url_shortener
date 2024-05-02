package org.example.repo.url;

import org.example.jdbc.jdbcUtils;
import org.example.repo.url.UrlDao.UrlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UrlRepositoryImp implements UrlRepository {
    @Override
    public String addUrl(UrlDao urlDao) throws SQLException {
        // TODO: Добваить проверку, что shortUrl - действительно ссылка.
        Connection connection = jdbcUtils.getConnection();
        String query = "INSERT INTO urls (shortUrl, longUrl) " +
                "VALUES (?, ?)";
        PreparedStatement preparedStatement =connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, urlDao.shortUrl());
        preparedStatement.setString(2, urlDao.longUrl());
        preparedStatement.execute();
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
             preparedStatement) {
            if (generatedKeys.next()) {
                return generatedKeys.getString(2);
            }
        }
        return null;
    }
    public Optional<UrlDao> getLongUrl(String shortUrl) throws SQLException{
        Connection connection = jdbcUtils.getConnection();
        String query = "SELECT shortUrl, longUrl FROM urls WHERE shortUrl=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1, shortUrl);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            UrlDao urlDao = new UrlDao(resultSet.getString("longUrl"),
                    resultSet.getString("shortUrl"));
            return Optional.of(urlDao);
        }
        return Optional.empty();
    }
    public boolean existShortUrl(String shortUrl) throws SQLException{
        Optional<UrlDao> result = getLongUrl(shortUrl);
        return result.isPresent();
    }

}
