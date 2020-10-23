package pl.sytomczak.fokusmanager.notes;

import pl.sytomczak.fokusmanager.dbutils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotepadModel {
    private Connection connection;

    public boolean isDatabaseConnecter() {
        return this.connection != null;
    }

    public NotepadModel() {
        this.connection = DBConnection.getConnection();
    }

    public NotepadItem getItemByTitle(String title) throws SQLException {
        String msg = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement("SELECT * FROM notes WHERE title = ?");
            ps.setString(1, title);

            rs = ps.executeQuery();
            if (rs.next())
                return new NotepadItem(rs.getString(1), rs.getString(2));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }


    public NotepadItem getItemByText(String text) throws SQLException {
        String msg = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement("SELECT * FROM notes WHERE text = ?");
            ps.setString(1, text);

            rs = ps.executeQuery();
            if (rs.next())
                return new NotepadItem(rs.getString(1));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    public ArrayList<NotepadItem> getItems() throws SQLException {
        String msg = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<NotepadItem> items = new ArrayList<NotepadItem>();

        try {
            ps = this.connection.prepareStatement("SELECT * FROM notes");

            rs = ps.executeQuery();
            while (rs.next())
                items.add(new NotepadItem(rs.getString(1), rs.getString(2)));

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    public boolean AddText(String title, String text) throws SQLException {
        String msg = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement("INSERT INTO notes (title, text) VALUES (?,?)");
            ps.setString(1, title);
            ps.setString(2, text);
            if (ps.executeUpdate() != 0)
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    public boolean Exist(String title) throws SQLException {
        String msg = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement("SELECT Count(title) FROM notes WHERE ?");
            ps.setString(1, title);

            rs = ps.executeQuery();
            Integer n = rs.getInt(1);
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    public boolean Update(String title, String newText) throws SQLException {
        String msg = "";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement("UPDATE notes SET text = ? WHERE title = ?");
            ps.setString(1, newText);
            ps.setString(2, title);

            if (ps.executeUpdate() != 0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

}
