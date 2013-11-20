package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(CatOrigenDatosController)
@Mock(CatOrigenDatos)
class CatOrigenDatosControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/catOrigenDatos/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.catOrigenDatosInstanceList.size() == 0
        assert model.catOrigenDatosInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.catOrigenDatosInstance != null
    }

    void testSave() {
        controller.save()

        assert model.catOrigenDatosInstance != null
        assert view == '/catOrigenDatos/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/catOrigenDatos/show/1'
        assert controller.flash.message != null
        assert CatOrigenDatos.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/catOrigenDatos/list'

        populateValidParams(params)
        def catOrigenDatos = new CatOrigenDatos(params)

        assert catOrigenDatos.save() != null

        params.id = catOrigenDatos.id

        def model = controller.show()

        assert model.catOrigenDatosInstance == catOrigenDatos
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/catOrigenDatos/list'

        populateValidParams(params)
        def catOrigenDatos = new CatOrigenDatos(params)

        assert catOrigenDatos.save() != null

        params.id = catOrigenDatos.id

        def model = controller.edit()

        assert model.catOrigenDatosInstance == catOrigenDatos
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/catOrigenDatos/list'

        response.reset()

        populateValidParams(params)
        def catOrigenDatos = new CatOrigenDatos(params)

        assert catOrigenDatos.save() != null

        // test invalid parameters in update
        params.id = catOrigenDatos.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/catOrigenDatos/edit"
        assert model.catOrigenDatosInstance != null

        catOrigenDatos.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/catOrigenDatos/show/$catOrigenDatos.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        catOrigenDatos.clearErrors()

        populateValidParams(params)
        params.id = catOrigenDatos.id
        params.version = -1
        controller.update()

        assert view == "/catOrigenDatos/edit"
        assert model.catOrigenDatosInstance != null
        assert model.catOrigenDatosInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/catOrigenDatos/list'

        response.reset()

        populateValidParams(params)
        def catOrigenDatos = new CatOrigenDatos(params)

        assert catOrigenDatos.save() != null
        assert CatOrigenDatos.count() == 1

        params.id = catOrigenDatos.id

        controller.delete()

        assert CatOrigenDatos.count() == 0
        assert CatOrigenDatos.get(catOrigenDatos.id) == null
        assert response.redirectedUrl == '/catOrigenDatos/list'
    }
}
